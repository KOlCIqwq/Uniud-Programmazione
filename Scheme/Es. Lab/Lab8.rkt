;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Lab8) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss" "installed-teachpacks") (lib "Drawing.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss" "installed-teachpacks") (lib "Drawing.ss" "installed-teachpacks")) #f)))
; The code is kinda buggy in the second part, need some fix
; 04/04/24 Update: Completed

(define (hanoi-disks n k) ; n for number of disks
    (hanoi-rec-disks-more-info n k 1 2 3 0 0 0 null null null))

(define (hanoi-rec-disks n k s d t n1 n2 n3) ; s, d, t are source, destination, and temporary rods; n1, n2, n3, are numbers of disks in each rod
    (cond [(= n 0) (list (list s n1) (list d n2) (list t n3))] ; Base case: when there are no disks, return the list of moves
          [(< k (expt 2 (- n 1))) ; When not last disk
           (hanoi-rec-disks (- n 1) k s t d (+ n1 1) n3 n2)] 
          [else
           (hanoi-rec-disks (- n 1) (- k (expt 2 (- n 1))) t d s n3 (+ n2 1) n1)] ; Move the last disk
          ))


(define (hanoi-rec-disks-more-info n k s d t n1 n2 n3 l1 l2 l3) ; Added l1 l2 l3 as lists of sizes of disk in each rod
    (cond [(= n 0) (list (list s n1 l1) (list d n2 l2) (list t n3 l3))]
          [(< k (expt 2 (- n 1)))
           (hanoi-rec-disks-more-info (- n 1) k s t d (+ n1 1) n3 n2 (cons n l1) l3 l2)]
          [else
           (hanoi-rec-disks-more-info (- n 1) (- k (expt 2 (- n 1))) t d s n3 (+ n2 1) n1 l3 (cons n l2) l1)]
          ))


; d = size, n = number of disk, p = rod, t = height
(define (rod-builder d n p t)
  (cond [(= (length d) 1) (disk-image (car d) n p t)]
        [else(above (disk-image (car d) n p t) (rod-builder (cdr d) n p (- t 1)))]
      )
  )

(define (hanoi-pictures n k x)
  (if (> x 2)
      (towers-background n)
      (let [(list (list-ref(hanoi-rec-disks-more-info n k 1 2 3 0 0 0 null null null)x))]
        (let [(d (list-ref list 2)) ; Weight of each disks
              (p (list-ref list 0)) ; Which rod
              (t (list-ref list 1))] ; How many disks
          (if (null? d)
              (hanoi-pictures n k (+ x 1))
              (above (rod-builder d n p t) (hanoi-pictures n k (+ x 1)))
              )
          ))
    ))

(define (hanoi-picture n k)
  (hanoi-pictures n k 0))
       