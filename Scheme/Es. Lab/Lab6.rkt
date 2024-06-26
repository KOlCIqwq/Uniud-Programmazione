;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Lab6) (read-case-sensitive #t) (teachpacks ((lib "Drawing.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "Drawing.ss" "installed-teachpacks")) #f)))
;;   (shift-down <figura> <passi>)
;;
;;   (shift-right <figura> <passi>)
;;
;;   (quarter-turn-right <figura>)
;;
;;   (quarter-turn-left <figura>)
;;
;;   (half-turn <figura>)
;;
;;   (glue-tiles <figura> <figura>)

;; insieme due figure: si capisce facilmente provando, per esempio:
;;
;;   (glue-tiles L-tile (shift-right (half-turn L-tile) 1))



(set-tessellation-shift-step!)

;This is a L-tesselation 2, rotating and moving L-tile we can built it from L-tile
(define base1
(glue-tiles  (shift-down (quarter-turn-left L-tile) 2)
 (glue-tiles (shift-right (quarter-turn-right L-tile) 2)
  (glue-tiles L-tile 
    (shift-down (shift-right L-tile 1)1))
  )
 ))

(define (power-two? n)
  (if (<= n 0)
      #f
      (if (= n 1)
          #t
          (and (integer? (/ n 2))
               (power-two? (/ n 2))))
      )
  )

;Recursion from L-tile 1, then 2, then 4,...
(define (L num)
  (if (power-two? num)
      (if (= num 1)
          L-tile  
      (glue-tiles
      (glue-tiles
      (glue-tiles
                 ; Top-Left tile
                 (L (/ num 2))
                 ; Top-Right tile
                 (shift-right (quarter-turn-right (L (/ num 2))) num))
                 ;Bottom-Left tile
                 (shift-down (quarter-turn-left (L (/ num 2))) num))
                 ;Center
                 (shift-down (shift-right (L (/ num 2)) (/ num 2)) (/ num 2))
      ))
      "Not power of two!"
      )
  ) 
    