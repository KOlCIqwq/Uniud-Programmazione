;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Prova esame1|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;Es1
(define match
(lambda (u v)
(if (or (string=? u "") (string=? v ""))
    ""
    (let ((uh (string-ref u 0)) (vh (string-ref v 0))
           (s (match (substring u 1) (substring v 1)))
           )
      (if (char=? uh vh)
          (string-append (string uh) s)
          (string-append "*" s)
          ))
    )))
;Es2
(define offset (char->integer #\0))
(define last-digit
  (lambda (base) (integer->char (+ (- base 1) offset))
    ))
(define next-digit
  (lambda (dgt) (string(integer->char (+ (char->integer dgt) 1)))
    ))
(define increment
  (lambda (num base) ; 2 <= base <= 10
    (let ((digits (string-length num)))
      (if (= digits 0)
          "1"
          (let ((dgt (string-ref num (- digits 1))))
            (if (char=? dgt (last-digit base))
                (string-append (increment(substring num 0 (- digits 1)) base) 
                 "0")
                (string-append (substring num 0 (- digits 1))(next-digit dgt))
                ))
          ))))
;Es3
(define lcs ; valore: lista di terne
  (lambda (u v) ; u, v: stringhe
    (lcs-rec 1 u 1 v)
    ))
(define lcs-rec
  (lambda (i u j v)
    (cond ((or (string=? u "") (string=? v ""))
           null)
          ((char=? (string-ref u 0) (string-ref v 0))
           (cons (list i j (string-ref u 0)) 
            (lcs-rec (+ i 1) (substring u 1) (+ j 1) (substring v 1)) ))
          (else
           (better (lcs-rec (+ i 1) u j (substring v 1)) ; confronta due casi: avanzare sulla stringa u o sulla stringa v
                   (lcs-rec i (substring u 1) (+ j 1) v))
           ))))
(define better
  (lambda (x y)
    (if (< (length x) (length y)) y x)
    ))
;Es4
(define (cyclic-string str n)
  (if (< (string-length str) n)
      (string-append(multiple str (quotient n (string-length str))) (substring str 0 (remainder n (string-length str))))
      (substring str 0 n)
      ))
(define (multiple str num)
  (if (= num 1)
      str
      (string-append str (multiple str (- num 1)))
      ))
;Es6
(define (shared lst1 lst2)
  (if (null? lst1)
      null
      (if (member? (car lst1) lst2)
          (cons (car lst1) (shared (cdr lst1) lst2))
          (shared (cdr lst1) lst2)
          )))
;Es7
(define (parity-check-failures lst)
  (parity lst 0))
(define (parity lst k)
  (if (null? lst)
      null
      (append (f (car lst) k) (parity (cdr lst) (+ k 1)))
)
  )
(define (f str k)
  (if (=(remainder(count str 0) 2)0)
      null
      (list k)
      )
  )
(define (count str i)
  (if (=(string-length str)0)
      i
  (if (char=? (string-ref str 0) #\0)
      (count (substring str 1) i)
      (count (substring str 1) (+ 1 i))
      ))
  ) 
;Es8
(define (sorted-char-list str)
  (if (=(string-length str)0)
      null
      (repeat-clean-up(sorted-list (string->list str)))
      )
  )

(define (sorted-list lst)
  (cond [(<= (length lst) 1) lst] ; List has only 1 member print lst
   (else (merge (sorted-list (take lst (quotient (length lst) 2))) (sorted-list (drop lst (quotient (length lst) 2))))) ;Divede the list into two parts
   )
  )

;Cut the list at n pos
(define (take lst n) ; Not defined in Teaching Language, but defined in Racket language, 
  (if (= n 0) '()    ; so it's a function that gives a fresh list which stops at n element
      (cons (car lst) (take (cdr lst) (- n 1)))))

;Drop the rest
(define (drop lst n) ;Not defined, it's a function to drop the element after n
  (if (= n 0) lst
      (drop (cdr lst) (- n 1))))

(define (merge lst1 lst2) ;Merge two list
  (cond [(null? lst1) lst2] ;Null first part?
   [(null? lst2) lst1] ;Null second part?
   [(char<=? (car lst1) (car lst2)) (cons (car lst1) (merge (cdr lst1) lst2))] 
   (else (cons (car lst2) (merge lst1 (cdr lst2))))
   )
  )

(define (repeat-clean-up lst) ;Clean the duplicates
  (if (<(length lst) 2)
      lst
  (if (char=? (car lst) (second lst))
      (repeat-clean-up(cdr lst))
      (cons (car lst) (repeat-clean-up (cdr lst)))
      )))
;Es9
(define (clean-up lst)
  (if (null? lst)
      null
  (if (and(member? (car lst) (cdr lst)) (> (length lst) 0))
      (cons (car lst)(clean-up (eliminate (car lst) (cdr lst))))
      (cons (car lst) (clean-up (cdr lst)))
      )))
(define (eliminate str lst)
  (if (null? lst)
      null
  (if (string=? str (car lst))
     (eliminate str (cdr lst))
     (cons (car lst) (eliminate str (cdr lst))))))
