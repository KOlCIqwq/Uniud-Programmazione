;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname LiuLab8) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;Cut the list at n pos
(define (take lst n) ; Not defined in Teaching Language, but defined in Racket language, 
  (if (= n 0) '()    ; so it's a function that gives a fresh list which stops at n element
      (cons (car lst) (take (cdr lst) (- n 1)))))

;Drop the rest
(define (drop lst n) ;Not defined, it's a function to drop the element after n
  (if (= n 0) lst
      (drop (cdr lst) (- n 1))))

;;Check the position of the char returning k pos
(define position
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
      (cond [(= k 0) 0]
            [(char=? (list-ref lst k) c) k]
            [else (check-next c lst (- k 1))]
      )
      )
    )

(define hanoi-moves ; val: lista di coppie
  (lambda (n) ; n > 0 intero
    (hanoi-rec n 1 2 3)
    ))

(define hanoi-rec ; val: lista di coppie
  (lambda (n s d t) ; n intero, s, d, t: posizioni
    (if (= n 1)
        (list (list s d))
        (let ((m1 (hanoi-rec (- n 1) s t d))
              (m2 (hanoi-rec (- n 1) t d s))
              )
          (append m1 (cons (list s d) m2))
          ))
    ))

;first element of the list need to be subtracted and the second add
(define (hanoi-disks disk move)
  (hanoi disk move (list (cons 1 '(disk))'(2 0) '(3 0)) (hanoi-moves disk)))

(define (hanoi disk move lst lst-moves)
  (let [(length-moves (length lst-moves))]
    (hanoi disk move (second-pos (first (second lst-moves)) (first-pos (first (first lst-moves)) lst)) (car lst-moves))
    ))

(define (first-pos number lst)
  (let [(list1 (car lst))
        (list2 (list-ref lst 1))
        (list3 (list-ref lst 2))]
  (cond [(= number 1) (list (cons 1 (list(- (second list1)1))) list2 list3)]
        [(= number 2) (list list1 (cons 2 (list(- (second list2)1))) list3)]
        [(= number 3) (list list1 list2 (cons 3 (list(- (second list3)1))))]
        (else (error "nah"))
        )
  )
  )

(define (second-pos number lst)
  (let [(list1 (car lst))
        (list2 (list-ref lst 1))
        (list3 (list-ref lst 2))]
  (cond [(= number 1) (list (cons 1 (list(+ (second list1)1))) list2 list3)]
        [(= number 2) (list list1 (cons 2 (list(+ (second list2)1))) list3)]
        [(= number 3) (list list1 list2 (list(cons 3 (+ (second list3)1))))]
        (else (error "nah"))
        )
  )
  )
;Alternative??
(define hanoi-disks
  (lambda (n k)
    (let* ((initial-disks '((1 n) (2 0) (3 0))))
           (hanoi-iter n k 1 2 3 initial-disks))))
(define (hanoi-iter n k s d t disks)
      (cond
        ((= k 0) disks)
        ((< k (- (expt 2 n) 1))
         (let* ((mid (/ (expt 2 (- n 1)) 2))
                (source (if (< k mid) s t))
                (destination (if (< k mid) t d))
                (new-disks (hanoi-move disks source destination)))
           (hanoi-iter (- n 1) k s d t new-disks)))
        (else
         (let* ((mid (/ (expt 2 (- n 1)) 2))
                (source (if (< k mid) t d))
                (destination (if (< k mid) s t))
                (new-disks (hanoi-move disks source destination)))
           (hanoi-iter (- n 1) (- k mid) s d t new-disks)))))
    
(define (hanoi-move disks source destination)
      (let* ((source-disk (assoc source disks))
             (destination-disk (assoc destination disks))
             (source-disk-count (if source-disk (cdr source-disk) 0))
             (destination-disk-count (if destination-disk (cdr destination-disk) 0))
             (source-disk-count-after (- source-disk-count 1))
             (destination-disk-count-after (+ destination-disk-count 1))
             (new-disks (map
                          (lambda (disk)
                            (if (= (car disk) source)
                                (cons source source-disk-count-after)
                                (if (= (car disk) destination)
                                    (cons destination destination-disk-count-after)
                                    disk)))
                          disks)))
        new-disks))


                       
