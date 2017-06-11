SECTION .data

SEECTION .bss
x resb 4
y resb 4
x resb 4
y resb 4
x resb 4
y resb 4
x resb 4
y resb 4
x resb 4
y resb 4
x resb 4
y resb 4

SECTION .txt
global _start

_start:
mov x, 6
mov y, 1
SECTION .data
c: dw 6
cLen: equ $ - c

SEECTION .bss
x resb 4
y resb 4
x resb 4
y resb 4
x resb 4
y resb 4
x resb 4
y resb 4
x resb 4
y resb 4
x resb 4
y resb 4
x resb 4
y resb 4

SECTION .txt
global _start

_start:
mov x, 6
mov y, 1
