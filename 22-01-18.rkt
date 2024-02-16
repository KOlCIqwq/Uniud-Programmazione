;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname 22-01-18) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;Es 3
(define (powers-of-two num)
  (cond [(= num 0) null]
        [(= num 1) (list 1)]
        [else (calc num 1)]
        )
  )
(define (calc num k)
  (if (= k 0)
      null
      (if (> (power-two k) num)
          (if (=(- num (power-two (- k 1)))0)
              (cons (power-two (- k 1)) null)
              (cons (power-two (- k 1)) (calc (- num (power-two (- k 1))) 1)))
          (calc num (+ k 1)))
          ))

(define (power-two k)
  (if (<= k 1)
      1
      (* (power-two (- k 1)) 2)
      ))

;Es 4
(define manhattan-var ; val: intero
  (lambda (i j k) ; i, j, k: interi non negativi tali che k ≤ i e k ≤ j
    (let ((x (if (= i k) 0 (manhattan-var (- i 1) j k)))
          (y (if (= j k) 0 (manhattan-var i (- j 1) k)))
          (z (if (= k 0) 0 (manhattan-var i j (- k 1))))
          )
      (if (and (> i 0) (> j 0))
          (+ x y z) ; what to add here?
          1 
          ))
    ))