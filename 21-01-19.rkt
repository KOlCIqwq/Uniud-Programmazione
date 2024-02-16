;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname 21-01-19) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define f ; val: intero
  (lambda (x y) ; x ≥ 0, y > 0 interi
    (if (< x y)
        1
        (+ (f (- x 1) y) (f (- x y) y))
        )))

(define (palindrome-lev str)
  (cond [(string=? str "") 0]
        [(=(string-length str)1) 1]
        [else (count str 0)]
        )
  )
(define (count str k)
  (if (<(string-length str)2)
      (if (=(string-length str) 0)
          k
          (+ k 1))
      (if(char=?(string-ref str 0) (string-ref str (-(string-length str)1)))
         (count (substring str 1 (-(string-length str)1)) (+ k 1))
         (count (substring str 1 (-(string-length str)1)) k))
         )
      )
;Es3 
(define xlcs ; val: stringa
  (lambda (s t) ; s, t: stringhe
    (cond ((string=? s "") t)
          ((string=? t "") (string-append "/" (xlcs (substring s 1) t)))
          ((char=? (string-ref s 0) (string-ref t 0))
           (string-append "*" (xlcs (substring s 1) (substring t 1))))
          (else
           (better (string-append "*" (xlcs (substring s 1) t)) ;What should I append here??
                   (string-append "*" (xlcs s (substring t 1)))
                   ))
          )))
(define better
  (lambda (u v)
    (if (< (stars u) (stars v))
        u
        v
        )))
(define stars
  (lambda (q)
    (if (string=? q "")
        0
        (let ((n (stars (substring q 1))))
          (if (char=? (string-ref q 0) #\*) (+ n 1) n)
          ))))

;Es5
(define mh ; val: lista di interi
  (lambda (i j) ; i, j: interi non negativi
    (if (or (= i 0) (= j 0))
        (list 0)
        (append (md (- i 1) j) (mr i (- j 1)))
        )))
(define md ; md: passo precedente in giù
  (lambda (i j)
    (cond ((and (= i 0) (= j 0)) (list 0))
          ((= i 0) (list 1))
          ((= j 0) (list 0))
          (else
           (append (md (- i 1) j)
                   (map (lambda (x) (+ 1 x)) (mr i (- j 1)))
                   ))
          )))
(define mr ; md: passo precedente a destra
  (lambda (i j)
    (cond ((and (= i 0) (= j 0)) (list 0))
          ((= i 0) (list 0))
          ((= j 0) (list 1))
          (else
           (append (mr i (- j 1))
                   (map (lambda (x) (+ 1 x)) (md (- i 1) j)) ; Seems like +1 is not the correct answer, maybe add some cases?
            ))
          )))