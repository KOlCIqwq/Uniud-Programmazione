;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname LiuLab9) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;(define (cipher rot)
 ; (lambda (c)
  ;  (let [(a (char->integer c))]
   ; (if (<= (+ a rot) codZ)
    ;    (integer->char (+ a rot))
     ;   (integer->char (- a (- 26 rot)))
      ;  )
    ;)
  ;)
  ;)

;(map (lambda (x) (encryption x (cipher 3))) '("ABCD" "EFGH"))


(define encryption        ; valore: stringa
  (lambda (message rule)  ; message: stringa, rule: lettera -> lettera
    (if (= (string-length message) 0)
        ""
        (string-append
         (string (rule (string-ref message 0)))
         (encryption (substring message 1) rule)
         ))
    ))


(define (caesar-cipher c)
  (let [(a (char->integer c))]
    (if (<= (+ a 3) codZ)
        (integer->char (+ a 3))
        (integer->char (- a 23))
        )
    )
  )

(define codA (char->integer #\A))
(define codZ (char->integer #\Z))
    
;each letter need a specific case, not quick...
(define (latin-caesar-cipher1 c)
    (let [(a (char->integer c))]
    (cond [(or (< a (char->integer #\G))(> a (char->integer #\I))) (integer->char (+ a 3))]
          [(or (>= a (char->integer #\G)) (<= a (char->integer #\I))) (integer->char (+ a 5))]
          [(= a (char->integer #\X)) (char->integer #\C)]
          [(or (>= a (char->integer #\T))(<= a (char->integer #\V))) (integer->char(+ a 7))]
          (else "Inserted a non-latin letter") 
        )
    )
  )

;Caesar-cipher in latin letters
(define (latin-caesar-cipher c)
  (let [(latin-letters '(#\ #\A #\B #\C #\D #\E #\F #\G #\H #\I #\L #\M #\N #\O #\P #\Q #\R #\S #\T #\V #\X))]
     (if (< (+ (pos c latin-letters) 3) 21)
         (list-ref latin-letters (+ (pos c latin-letters) 3))
         (list-ref latin-letters (- (pos c latin-letters) 17))
    )
  )
  )
;;Check the position of the char returning k pos
(define pos
  (lambda (c lst)
    (let ((k (- (length lst) 1)))
          (if (char=? (list-ref lst k) c)
              k
              (check-next c lst k)
              )
    )
   )
  )
;Check next
(define check-next
  (lambda (c lst k)
      (cond [(= k 0) -3]
            [(char=? (list-ref lst k) c) k]
            [else (check-next c lst (- k 1))]
      )
      )
    )

;Identity 
(define (id x) x)
;s2 refering to succ
(define (s2 u v) (+ v 1))

;Main H function requiring f g as function, m n as inputs
(define (H f g)
  (lambda (m n)
    (if (= n 0)
        (f m)
        (g m ((H f g) m (- n 1))) ; g applied to m and recall H with n-1
        )
    )
  )
;add = H (identity -> x) (s2 -> v+1), m n as inputs
(define add (H id s2))

(define mul (H (lambda (x) 0) add))

(define pow (H (lambda (x) 1) mul))





  
  
