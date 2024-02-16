;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Coniugazione participio passato dei verbi|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define APartPst ;Substitute verb ending with "are"
  (lambda (verb)
    (string-append
     (substring verb 0 (-(string-length verb) 3)) "ato") ;Substitute last 3 characters with "ato"
  ))

(define EPartPst ;Substitute verb ending with "ere"
  (lambda (verb)
    (string-append
     (substring verb 0 (-(string-length verb) 3)) "uto") ;Substitute last 3 characters with "uto"
  ))

(define IPartPst ;Substitute verb ending with "ire"
  (lambda (verb)
    (string-append
     (substring verb 0 (-(string-length verb) 3)) "ito") ;Substitute last 3 characters with "ito"
  ))

;; Autodetect a verb's ending and change with right one.
(define PartPst 
  (lambda (verb)
       (if (char=? (string-ref verb (- (string-length verb) 3)) #\a) (APartPst verb) ; If true that 3rd last letter = "a" then convert it into "ato"
           (if (char=? (string-ref verb (- (string-length verb) 3)) #\e) (EPartPst verb) (IPartPst verb))
           ; If false check if it's ending with e if true then convert uto, if not ito
           )
  ))