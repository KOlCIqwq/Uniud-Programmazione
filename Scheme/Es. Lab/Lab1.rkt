;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Lab1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; Fare un check dei due soggetti se sono prulari e se il primo soggetto è prulare allora verbo aggiungerà "no" alla fine
; Convertire verbo da infinito a "are->a" "ere->e" "ire->e"
; articolo+soggetto viene visto come un unica stringa da fare append

(define unione ; unione del risultato
  (lambda (Soggetto1 Verbo Soggetto2) ; richiede 3 stringhe 
    (let ((Last_L1 (string-ref Soggetto1 (- (string-length Soggetto1)1))) (Last_L2 (string-ref Soggetto2 (- (string-length Soggetto2)1))))
      (if (or (char=? Last_L1 #\e) (char=? Last_L1 #\i)) ; se ultima lettera del soggetto1 e' uguale a e oppure i allora prulare
          (string-append (aggiungi_art_prl Soggetto1) " " (coniugare_verb_prl Verbo) " " (if (or (char=? Last_L2 #\e) (char=? Last_L2 #\i))
                             (aggiungi_art_prl Soggetto2)
                             (aggiungi_art_sin Soggetto2))) ; determina se è singolare o prulare soggetto2 mettendo oppurtuno articolo
          ; nel case se tutto fosse singolare
          (string-append (aggiungi_art_sin Soggetto1) " " (coniugare_verb_sin Verbo) " " (aggiungi_art_sin Soggetto2)) 
          )
      )
    ))

(define aggiungi_art_prl ; aggiunge articoli prulari a soggetti prulari
  (lambda (Sog)
    (let
        ((Last_L (string-ref Sog (- (string-length Sog)1))))
      (if (char=? Last_L #\e)
    (string-append "le" " " Sog)
    (string-append "i" " " Sog)
      )
    )
  )
  )

(define aggiungi_art_sin ; aggiunge articoli singolari a soggetti singolari
  (lambda (Sog)
    (let ((Last_L (string-ref Sog (- (string-length Sog)1))))
      (if (char=? Last_L #\o)
    (string-append "il" " " Sog)
    (string-append "la" " " Sog)
      )
    )
  )
  )

(define coniugare_verb_prl ; coniuga il verbo in prulare
  (lambda (verb)
    (let ((Verb_L (string-ref verb (- (string-length verb)3)))
          (Sub_re (substring verb 0 (- (string-length verb)3))))
      (cond [(char=? Verb_L #\a) (string-append Sub_re "ano")] ; Se ultima lettera e' a, allora togli ultima lettera(sub_re) e append "ano"
          (else (string-append Sub_re "ono") 
                ) 
          )
      )
    )
  )

(define coniugare_verb_sin ; toglie "re" al fine del verbo
  (lambda (verb)
    (let ((Verb_L (string-ref verb (- (string-length verb)3))))
         (substring verb 0 (- (string-length verb)2))      
    )
  )
  )



      