;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname 04-02-20) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define f ; val: intero
  (lambda (x y u v) ; x, y ≥ 0 interi; u, v interi
    (cond ((and (= u 0) (= v 0)) 0)
          ((= x 0) (if (= u 0) 0 1))
          ((= y 0) (if (= v 0) 0 1))
          (else (+ (f (- x 1) y (- u 1) v) (f x (- y 1) u (- v 1))))
          )))

;Es2
(define (standard-form lst)
  (if (null? lst)
      '()
      (cons(Cap (car lst)) (standard-form (cdr lst)))
      )
  )
  
(define (Cap str)
  (if (char-alphabetic? (string-ref str 0))
      (if (and(>(char->integer(string-ref str 0))65)(<(char->integer(string-ref str 0))91))
          str
          (string-append (string(integer->char(-(char->integer (string-ref str 0))32))) (substring str 1))
          )
      "Not aplhabetic"
      )
  )
;Es3
(define btr-val-tr ; val: intero
  (lambda (btr) ; btr: stringa di – / . / +
    (btr-val-rec btr )
    ))
(define btr-val-rec
  (lambda (btr)
    (let ((k (string-length btr)))
      (if (= k 0)
          0
          (let ((q (substring btr 1))
                (t (string-ref btr 0))
                )
            (btr-val-rec q ) ; What I should put here to convert t??
            )))
    ))

;Es5
(define combinations ; val: lista di stringhe
  (lambda (k n) ; k, n: interi non negativi
    (if (= n 0)
        (list (make-string n #\0))
        (let ((u (if (= k 0)
                     null
                     (combinations (- k 1) (- n 1))
                     ))
              (v (combinations k (- n 1)))
              )
          (append 
           (map (lambda (x) (string-append "1" x)) u)
           (map (lambda (x) (string-append "0" x)) v)
           )))
    ))

(combinations 0 3)

