;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Lab5) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;no z-axis
(define manhattan-xy      
  (lambda (down right) 
    (if (or (= down 0) (= right 0))
        1
        (+ (manhattan-xy (- down 1) right)
           (manhattan-xy down (- right 1)))
        )
    ))
;no x-axis
(define manhattan-yz       
  (lambda (down depth)  
    (if (or (= down 0) (= depth 0))
        1
        (+ (manhattan-yz (- down 1) depth)
           (manhattan-yz down (- depth 1)))
        )
    ))
;no y-axis
(define manhattan-xz       
  (lambda (right depth)  
    (if (or (= right 0) (= depth 0))
        1
        (+ (manhattan-xz (- right 1) depth)
           (manhattan-xz right (- depth 1)))
        )
    ))
;let each of them go to each direction then sum the result 
(define manhattan-3d
  (lambda (depth down right)
    (cond [(= depth 0) (manhattan-xy down right)]
          [(= right 0) (manhattan-yz down depth)]
          [(= down 0) (manhattan-xz right depth)]
          [else (+ (manhattan-3d (- depth 1) down right) (manhattan-3d depth (- down 1) right) (manhattan-3d depth down (- right 1)) )]
          )
    )
  )