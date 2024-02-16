;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname LiuLab2) (read-case-sensitive #t) (teachpacks ((lib "Drawing.rkt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "Drawing.rkt" "installed-teachpacks")) #f)))
(set-puzzle-shift-step!)

;Croce
(glue-tiles (glue-tiles (shift-down larger-tile 0) (shift-right smaller-tile 2))
            (glue-tiles (shift-right(shift-down (half-turn smaller-tile) 5) 2) (shift-right(shift-down(half-turn larger-tile) 1)2))
            )

;Rettangolo
(glue-tiles smaller-tile (half-turn smaller-tile))

;Quadrato
(glue-tiles(glue-tiles (glue-tiles (shift-down larger-tile 0) (shift-right smaller-tile 2))
            (glue-tiles (shift-right(shift-down (half-turn smaller-tile) 5) 2) (shift-right(shift-down(half-turn larger-tile) 1)2)))
           (glue-tiles(shift-right(glue-tiles (shift-right smaller-tile 2) (shift-right (half-turn smaller-tile)2))2)
           (shift-down(shift-right(glue-tiles (shift-right smaller-tile 2) (shift-right (half-turn smaller-tile)2))2)1)))
;Basta fare glue di tutti altri lati mancanti
