;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Final exam 22-23|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;12-06-23
;Es 4
(define manhlist-x
  (lambda (i j)
    (if (and (= i 0) (= j 0))
        (list "")
        (append (map (lambda (x) (string-append "0" x))
                 (manhlist-rec true (- i 1) j)) ; primo spostamento in basso
                (map (lambda (x) (string-append "1" x))
                 (manhlist-rec false i (- j 1))) ; primo spostamento a ddestra
                )
        )))
(define manhlist-rec
  (lambda (down i j)
    (cond ((or (< i 0) (< j 0)) null)
          ((and (= i 0) (< j 2)) (list (if (= j 0) "" "1")))
          ((and (< i 2) (= j 0)) (list (if (= i 0) "" "0")))
          (else
           (append (map (lambda (x) (string-append (if down "0" "00") x))
                        (manhlist-rec true (- i (if down 1 2)) j)
                        )
                   (map (lambda (x) (string-append (if down "11" "1")x))
                        (manhlist-rec false i (- j (if down 2 1)))
                    )
                   ))
          )))
;20-07-23
;Es1
(define bin-trees ; val: lista degli alberi binari di struttura diversa con n foglie
  (lambda (n) ; n > 0 intero
    (if (= n 1)
        (list "f")
        (in-lists-merge
         (map (lambda (k) (all-trees-from (bin-trees k) (bin-trees (- n k))))
              (int-range 1 (- n 1))
              ))
        )))
(define all-trees-from ; val: lista degli alberi binari con sottoalberi sinistro in lst e destro in rst
  (lambda (lst rst) ; lst, rst: liste di alberi binari
    (in-lists-merge
     (map (lambda (u) (map (lambda (x) (string-append "@" u x)) rst))
          lst
          ))
    ))
(define in-lists-merge ; val: lista risultante dalla fusione (append) delle liste-elementi di v
  (lambda (v) ; v: lista di liste
    (if (null? v)
        null
        (apply append v)
        )))
(define int-range ; val: lista degli interi nell’intervallo [inf, sup]
  (lambda (inf sup) ; inf, sup: interi
    (if (> inf sup)
        null
        (cons inf (int-range (+ inf 1) sup))
        )))

;01-09-23
;Es1 (Half solved)
(define (longest-contiguous-repeat lst)
  (cond [(=(length lst)0) null]
        [(=(length lst) 1) (car lst)]
        [else (count lst (car lst) "" 0 0)]
        ))

(define (count lst CI LI CS LS) ;CI = current item LI = longest item CS = current streak LS = longest streak
  (if (<(length lst)2)
      LI
      (if (string=? (car lst) (second lst))
          (count (cdr lst) (car lst) LI (+ 1 CS) LS)
          (if (< CS LS)
              (count (cdr lst) (list-ref lst 1) LI 0 (+ 1 LS))
              (count (cdr lst) (list-ref lst 1) (car lst) 0 (+ 1 LS))
              )
          )
      )
  )

;Es 1
(define (alternate-merge lst1 lst2)
  (merge lst1 lst2 0))

(define (merge lst1 lst2 k)
  (cond [(or (null? lst1) (null? lst2))
         (if (null? lst1)
             lst2
             lst1)]
        [(= k 0) (cons (car lst1) (merge (cdr lst1) lst2 1))]
        [else (cons (car lst2) (merge lst1 (cdr lst2) 0))]
        ))

;Es2 (no interest in hanoi-tower..)