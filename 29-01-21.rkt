;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname 29-01-21) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;Es1
(define (pair num1 num2)
  (let [(x (/(+ num1 num2) 2))
    (y (if (>(- num1 num2) 0)
      (/ (- num1 num2) 2)
      (/ (*(- num1 num2) -1) 2)))]
    (list x y)
    )
  )

(define (pair-list lst1 lst2)
  (cond [(or(>(length lst1) (length lst2)) (>(length lst1) (length lst2))) "Enter two list with same length"]
        [(=(length lst1)0) null]
        [else (append (pair (car lst1) (car lst2)) (pair-list (cdr lst1) (cdr lst2)))]
        )
  )
;Es2
(define lcs-align ; val: coppia di liste di caratteri
  (lambda (u v) ; u, v: stringhe
    (let ((m (string-length u)) (n (string-length v)))
      (cond ((or (= m 0) (= n 0)) (list (string->list u) (string->list v)))
            ((char=? (string-ref u 0) (string-ref v 0)) (lcs-align (substring u 1) (substring v 1)))
            (else(let((du (lcs-align (substring u 1) v))
                   (dv (lcs-align u (substring v 1))))
               (if (> (+ (length (car du)) (length (cadr du))) (+ (length (car dv)) (length (cadr dv))))
                   (list (car dv) (cons (string-ref v 0) (cadr dv)))
                   (list (cons (string-ref u 0) (car du)) (cadr du))
                   )))
            ))))

(lcs-align "ac" "bc")

(define f ; val: intero
  (lambda (x y) ; x, y: interi positivi, tali che x ≤ y
    (if (= x y)
        (+ x y -1)
        (let ((z (quotient (+ x y) 2)))
          (+ (f x z) (f (+ z 1) y))
          ))
    ))

(define sq ; val: intero
  (lambda (n) ; n: intero positivo
    (f 1 n)
    ))

(define parity-check? ; val: booleano
  (lambda (words) ; words: lista non vuota di stringhe di 0/1 della stessa lunghezza
    (rec-check? words 0 (string-length (car words)))
    ))

(define rec-check?
  (lambda (words k n) ;words = lst, k = pointer, n = length of string 
    (if (< k n)
        (let ((kths (map (bit k) words))) ; kths: lista dei valori dei bit in posizione k nelle parole di words
          (if (even? (count-ones kths))
              (rec-check? words (+ k 1) n)
              false))
        true
        )))
(define bit
  (lambda (x) ; pointer
    (lambda (str)
    (string-ref str x)
    )))

(define count-ones
  (lambda (cs) ;lst = kths
    (if (null? cs)
        0
        (+ (char->integer(car cs)) (count-ones (cdr cs)))
        )))
