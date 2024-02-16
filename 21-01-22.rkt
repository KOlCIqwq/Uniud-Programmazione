;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname 21-01-21) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define (cyclic-pattern str num)
  (cond [(=(string-length str)0) ""]
        [(=(string-length str) num) str]
        [(<(string-length str) (* 2 num)) ""]
        [else (check str (substring str 0 num) (substring str num (+ num num)) num)]
        ))

(define (check str str1 str2 num)
  (cond [(=(string-length str) 0) str1]
        [(string=? str1 str2) (cyclic-pattern (substring str num) num)]
        [else ""]
        )
  )

(define (tess-1x-2 n)
  (if (< n 2)
      1
      (+ (tess-1x-2 (- n 3))
         (tess-1x-2 (- n 2))
         )
      )
  )

(define paths ; val: lista di stringhe
  (lambda (i j k) ; i, j, k: interi non negativi
    (paths-rec i j k k)
    ))

(define paths-rec
  (lambda (i j k v)
    (cond ((= i 0)(if (> j v) null (list(make-string j #\1))))
          ((= j 0) (list(make-string i #\0)))
          ((= v 0)(map (lambda (x) (string-append "1" x))(paths-rec (- i 1) j k k)))
          (else(append(map (lambda (x) (string-append "0" x))(paths-rec (- i 1) j k k))
            (map (lambda (x) (string-append "1" x))(paths-rec i (- j 1) k (- v 1)))))
          )
))