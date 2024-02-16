;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Lab8) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss" "installed-teachpacks")) #f)))
; This is code is not completed!!
(define (hanoi-disks n k) ; n for number of disks
    (hanoi-rec-disks n k 1 2 3 0 0 0))

(define (hanoi-rec-disks n k s d t n1 n2 n3) ; s, d, t are source, destination, and temporary rods; n1, n2, n3, are numbers of disks in each rod
    (cond [(= n 0) (list (list s n1) (list d n2) (list t n3))] ; Base case: when there are no disks, return the list of moves
          [(< k (expt 2 (- n 1))) ; If k is less than 2 raised to the power of (n - 1)
           (hanoi-rec-disks (- n 1) k s t d (+ n1 1) n3 n2)] ; Move the top (n - 1) disks from source to temporary using destination as an intermediary
          [else
           (hanoi-rec-disks (- n 1) (- k (expt 2 (- n 1))) t d s n3 (+ n2 1) n1)] ; Move the disks from temporary to destination using source as an intermediary
          ))

(define (hanoi-rec-disks-info n k s d t n1 n2 n3) 
    (cond [(= n 0) (list (list s n1) (list d n2) (list t n3))] 
          [(< k (expt 2 (- n 1)))
           (hanoi-rec-disks-info (- n 1) k s t d (+ n1 1) n3 n2)] 
          [else
           (hanoi-rec-disks-info (- n 1) (- k (expt 2 (- n 1))) t d s n3 (+ n2 1) n1)] 
          ))


(above (disk-image 1 5 1 4)
(above (disk-image 2 5 1 3)              
(above (disk-image 3 5 1 2)
(above (disk-image 4 5 1 1)
 (above (disk-image 5 5 1 0) (towers-background 5))))))