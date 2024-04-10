;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Lab7) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; Check if a number belongs to a list
(define belong?
  (lambda (num lst)
    (let ((k (- (length lst) 1)))
          (if (= (list-ref lst k) num)
              #T
              (check-next num lst k)
              )
    )
   )
  )
; Recursive with k pointer, in the original it was not requested
(define check-next
  (lambda (num lst k)
      (cond [(= (list-ref lst k) num) #T]
            [(= k 0) #F]
            (else (check-next num lst (- k 1)))
      )
      )
    )

;; Check position
(define position
  (lambda (num lst)
    (let ((k (- (length lst) 1)))
      (if (= (list-ref lst k) num)
          k
          (check-next-pos num lst k)
          )
      )
    )
  )
; Outputs the position instead of T or F

(define check-next-pos
  (lambda (num lst k)
      (cond [(= (list-ref lst k) num) k]
            [(= k 0) #F]
            (else (check-next-pos num lst (- k 1)))
      )
      )
    )

;; Check the list with the num if it already exist then it will remain unchanged else add it to list considering its order
(define sorted-ins
  (lambda (num lst)
    (let ((k (- (length lst) 1)))
    (cond [(null? lst) (cons num '())]
          [(belong? num lst) lst] 
          [(> (list-ref lst 0) num) (cons num lst)]
          [(< (list-ref lst k) num) (insert-kpos num lst length)]
          (else(greater? num lst k))
          )
      )
    )
  )

;Check if the k pos is greater of num, if yes just fit it into k pos
(define greater?
  (lambda (num lst k)
    (if (< num (list-ref lst k))
        (insert-kpos num lst k)
        (greater? num lst (- k 1))
        )
    )
  )

; Insert the num in k pos
(define insert-kpos
  (lambda (num lst k)
    (if (= k 0)
        (cons num lst)
         (if (null? lst)
         (list num)
         (cons (car lst) (insert-kpos num (cdr lst) (- k 1))) ; cons the digit before k pos, recursive with the remaining list and k-1 until k = 0
        )
    )
  )
  )

;; Order the list (Merge sort)
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
   [(<= (car lst1) (car lst2)) (cons (car lst1) (merge (cdr lst1) lst2))] ;If the first element of the first part is smaller than second part Cons the first of lst1 and the rest of lst1 + lst2
   (else (cons (car lst2) (merge lst1 (cdr lst2)))) ;If the second part is smaller then first element of lst2 + lst1 + rest lst2 
   )
  )

;Debug
;(sorted-list '(4 2 7 1 5))
