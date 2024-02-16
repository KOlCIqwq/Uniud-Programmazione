;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname LiuLab3) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;Teaching Language does not contain some of the following functions

;https://docs.racket-lang.org/reference/strings.html#%28def._%28%28lib._racket%2Fstring..rkt%29._string-contains~3f%29%29
(define string-contains
  (lambda (str char)
    (cond [(= (string-length str) 0) #f]
          [(char=? (string-ref str 0) char) #t]
          (else (string-contains (substring str 1) char))
          )
    )
  )
;https://docs.racket-lang.org/reference/characters.html#%28def._%28%28quote._~23~25kernel%29._char-~3einteger%29%29
(define char-to-int
  (lambda (c)
  (- (char->integer c) (char->integer #\0))
  )
  )
;https://docs.racket-lang.org/reference/characters.html#%28def._%28%28quote._~23~25kernel%29._integer-~3echar%29%29
(define (int-to-char n)
  ;(if (and (integer? n) (>= n 0) (< n 16))
      (if (< n 10)
          (integer->char(+ n (char->integer #\0)))
          (integer->char(+ (- n 10) (char->integer #\A)))) 
   ;   (error "Input must be a non-negative integer less than 16")))
  )
;https://docs.racket-lang.org/reference/pairs.html#%28def._%28%28lib._racket%2Flist..rkt%29._take%29%29
(define (take lst n) 
  (if (= n 0) '()    
      (cons (car lst) (take (cdr lst) (- n 1)))))
;https://docs.racket-lang.org/reference/pairs.html#%28def._%28%28lib._racket%2Flist..rkt%29._drop%29%29
(define (drop lst n)
  (if (= n 0) lst
      (drop (cdr lst) (- n 1))))


;;Check if a char belongs to a list
(define belong?
  (lambda (c lst)
    (let ((k (- (length lst) 1)))
          (if (char=? (list-ref lst k) c)
              #T
              (check-next c lst k)
              )
    )
   )
  )
;Check if next char belong to the list
(define check-next
  (lambda (c lst k)
      (cond [(char=? (list-ref lst k) c) #T]
            [(= k 0) #F]
            [else (check-next c lst (- k 1))]
      )
      )
    )

;;Same as above but it requires a integer as input rather char (maybe I can add a condition on the code above to make it work the same?)
(define belong1?
  (lambda (c lst)
    (let ((k (- (length lst) 1)))
          (if (char=? (int-to-char(list-ref lst k)) c)
              #T
              (check-next1 c lst k)
              )
    )
   )
  )

(define check-next1
  (lambda (c lst k)
      (cond [(char=? (int-to-char(list-ref lst k)) c) #T]
            [(= k 0) #F]
            (else (check-next1 c lst (- k 1)))
      )
      )
    )

;;#\10 cannot be written as a char so here's a conversion to integer of the list transforming A or a to 10, B b to 11,...
(define (listchar-to-listint lst)
  (if (null? lst)
      '()
      (cond [(and (> (char-to-int (first lst)) 16) (< (char-to-int (first lst)) 41))  (cons (- (char-to-int (first lst)) 7) (listchar-to-listint (cdr lst)))]
            [(and (> (char-to-int (first lst)) 48) (< (char-to-int (first lst)) 74))  (cons (- (char-to-int (first lst)) 39) (listchar-to-listint (cdr lst)))]
            [else (cons (char-to-int (first lst)) (listchar-to-listint (cdr lst)))]
      )
  )
  )


;;Rep->number
(define (rep->number base s)
  (let [(s (listchar-to-listint (T base s 0)))
        (base (string->list base))]
    (if (belong1? #\. s) 
        (cond [(= (first s) -3) (* -1 (+ (Adot (cdr s) (length base)) (Bdot (cdr s) (length base))))]
              [(= (first s) -5) (+ (Adot (cdr s) (length base)) (Bdot (cdr s) (length base)))]
              [(= (length base) 10) s]
              [else (+ (Adot (cdr s) (length base)) (Bdot s (length base)))])
        ; if the string does not contain fractional part:
        (cond [(= (first s) -3) (* -1 (Bdot s (length base)))]
              [(= (first s) -5) (Bdot s (length base))]
              [(= (length base) 10) s]
              [else (Bdot s (length base))])
    )
  )
  )

;Transform the string into a list following the base
;K is 0 for first cycle, which converts all the letters that contain the first letter of base
;"uz" "uzuz"-> '(#\u #\z #\u #\z) -> '(#\0 #\z #\u #\z) -> '(#\0 #\z #\0 #\z) -> '(#\0 #\1 #\0 #\z) -> '(#\0 #\1 #\0 #\1)
;            k = 0                                                           "z" "0z0z" k = 1                           end

(define (T base s k)
  (let [(base (string->list base))
        (s (string->list s))]
    (if (null? base)
        s
        (T (list->string(cdr base)) (list->string(Transform base s k)) (+ k 1))
    )
  )
  ) 

(define (Transform base s k)
  (if (null? s)
      null
      (if (belong? (first base) s)
          (if (char=? (first s) (first base))
              (cons (int-to-char k) (Transform base (cdr s) k))
              (cons (car s) (Transform base (cdr s) k)))
          s
          )
    )
  )


;AfterDot
(define Adot
  (lambda (s base)
    (let ((k (length s)))
      (if (null? s)
          0
          (if (belong1? #\. s)
              (Adot (cdr s) base)
              (if (<= k 0)
                  0
                  (+ (/(list-ref s (- k 1)) (expt base k)) (Adot (take s (- k 1)) base))
                  )
              )
          )
      )
  )
  )

;BeforeDot
(define Bdot
  (lambda (s base)
    (let ((k (- (length s) 1)))
      (if (null? s)
          0
      (if (belong1? #\. s)
          (Bdot (take s k) base)
          (if (< k 0)
              0
              (+ (* (expt base k) (car s)) (Bdot (cdr s) base))
          ))
      )
    )
  )
  )




