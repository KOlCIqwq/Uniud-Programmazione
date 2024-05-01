;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Lab9) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
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

;Init function
(define (latin-caesar-cipher str)
  (latin-caesar-cipher-rec str 0 "")
  )
;To let input be a string
(define (latin-caesar-cipher-rec str k out)
  ;Its a for loop with rec, k goes from 0 to length and out is updating every time the function got recalled
  (if (= k (string-length str))
      out
      (latin-caesar-cipher-rec str (+ k 1) (string-append out (string(latin-caesar-cipher-char (string-ref str k)))))
      ))

;We give a list of char containing only latin letters, and we shift them in base of their pos in the list
;If not a latin char we output space
;Caesar-cipher in latin letters
(define (latin-caesar-cipher-char c)
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
;It recalls H decrementing n to 0 and applying +1 to m each time H function is recalled
;(add 1 2)-> (s2 1 (add 1 1))->(s2 1 (s2 1 (add 1 0)))->(s2 1 (s2 1 (1)))->(s1 1(2))->3
(define add (H id s2))

;Uses add function to increment itself
;(mul 3 2)->(add 3 (mul 3 1))->(add 3 (add 3 (mul 3 0)))->(add 3 (add 3 (0)))->(add 3 (3))->(s2 3 (add 3 2)))->...->6
(define mul (H (lambda (x) 0) add))
;(pow 3 2)->(mul 3 (pow 3 1))->(mul 3 (mul 3 (pow 3 0)))->(mul 3 (mul 3 1))->(mul 3 (add 3 0))->(mul 3 3)->(add 3 (mul 3 2))->...->9 
(define pow (H (lambda (x) 1) mul))




  
  
