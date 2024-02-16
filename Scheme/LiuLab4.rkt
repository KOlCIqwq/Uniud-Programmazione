;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname LiuLab4) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;Toggle the #\. at the front
(define (normalized-btr s)
  (if (or (= (string-length s) 1) (string=? s ""))
      s
      (if (char=? (string-ref s 0) #\.)
          (normalized-btr (substring s 1))
          s
          )
      )
  )

;If the string is empty, it returns '.' otherwise, it retrieves the last character
(define (lsd s)
  (if (string=? s "")
      #\.
      (string-ref s (- (string-length s) 1))
      )
  )

;Exclude the last character 
(define (head s)
  (if (string=? s "")
      ""
      (substring s 0 (- (string-length s) 1)))
  )

;BTR sum
(define (btr-sum s1 s2)
  (normalized-btr (btr-carry-sum s1 s2 #\.))
  )

(define (btr-carry-sum s1 s2 c)
  (cond [(and (string=? s1 "") (string=? s2 "")) (string c)]
        [else (string-append (btr-carry-sum (head s1) (head s2) (btr-carry (lsd s1) (lsd s2) c))
                             (string (btr-digit-sum (lsd s1) (lsd s2) c)))]
        )
  )

(define btr-digit-sum                    ; val:     carattere +/./-
  (lambda (u v c)                        ; u, v, c: caratteri +/./-
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\.)
                        ((char=? c #\.)  ; - - .
                         #\+)
                        ((char=? c #\+)  ; - - +
                         #\-)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\+)
                        ((char=? c #\.)  ; - . .
                         #\-)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  c)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\+)
                        ((char=? c #\.)  ; . - .
                         #\-)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  c)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\+)
                        ((char=? c #\+)  ; . + +
                         #\-)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  c)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\+)
                        ((char=? c #\+)  ; + . +
                         #\-)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\+)
                        ((char=? c #\.)  ; + + .
                         #\-)
                        ((char=? c #\+)  ; + + +
                         #\.)))))
          )))

(define btr-carry                   ; val:     carattere +/./-
  (lambda (u v c)                        ; u, v, c: caratteri +/./-
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\-)
                        ((char=? c #\.)  ; - - .
                         #\-)
                        ((char=? c #\+)  ; - - +
                         #\.)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\-)
                        ((char=? c #\.)  ; - . .
                         #\.)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  #\.)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\-)
                        ((char=? c #\.)  ; . - .
                         #\.)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  #\.)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\.)
                        ((char=? c #\+)  ; . + +
                         #\+)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  #\.)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\.)
                        ((char=? c #\+)  ; + . +
                         #\+)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\.)
                        ((char=? c #\.)  ; + + .
                         #\+)
                        ((char=? c #\+)  ; + + +
                         #\+)))))
          )))