mov n ,ebx
SECTION .data
x: dw 4
xLen: equ $ - x

SEECTION .bss
y resb 4
n resb 4

SECTION .txt
global _start

_start:
mov ebx, 1
add ebx, 8
mov n ,ebx
