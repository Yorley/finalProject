SECTION .data
x: dw 4
xLen: equ $ - x

SEECTION .bss
y resb 4
n resb 4

SECTION .txt
global _start

_start:
mov n, 8
mov y, 0
SECTION .data
x: dw 4
xLen: equ $ - x

SEECTION .bss
y resb 4
n resb 4

SECTION .txt
global _start

_start:
mov n, 8
mov y, 0
