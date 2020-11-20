@echo off
cd "%~dp0"
IF EXIST "%~dp0\bin" SET PATH=%PATH%;"%~dp0\bin"
Setlocal EnableDelayedExpansion
set "folder=boot"
set "file=boot.img"
copy project\boot.img "%~dp0"
:unpack
if exist "%folder%" rd /s/q "%folder%"
md %folder%
bin\python3\python.exe bin\unpackbootimg.py -i %file% -o %folder%
:donecheck
cd %folder%
for %%a in ("%file%-ramdisk.*") do set ext=%%~xa
type nul > %file%-ramdisk-compress
echo %ext:~1% > "%file%-ramdisk-compress"
md ramdisk
goto %ext:~1%
:gz
cd ramdisk
busybox gzip -dc "../%file%-ramdisk.gz" | busybox cpio -i
if %errorlevel% neq 0 goto ziperror
cd..\
del "%file%-ramdisk.gz"
cd..\
goto end
:lzma
cd ramdisk
busybox lzma -dc "../%file%-ramdisk.lzma" | busybox cpio -i
if %errorlevel% neq 0 goto ziperror
cd..\
del "%file%-ramdisk.lzma"
cd..\
goto end
:xz
cd ramdisk
busybox xz -dc "../%file%-ramdisk.xz" | busybox cpio -i
if %errorlevel% neq 0 goto ziperror
cd..\
del "%file%-ramdisk.xz"
cd..\
goto end
:bz2
cd ramdisk
busybox bzip2 -dc "../%file%-ramdisk.bz2" | busybox cpio -i
if %errorlevel% neq 0 goto ziperror
cd..\
del "%file%-ramdisk.bz2"
cd..\
goto end
:lz4
cd ramdisk
lz4 -d "../%file%-ramdisk.lz4" stdout | busybox cpio -i
if %errorlevel% neq 0 goto ziperror
cd..\
del "%file%-ramdisk.lz4"
cd..\
goto end
:lzo
cd ramdisk
busybox lzop -dc "../%file%-ramdisk.lzo" | busybox cpio -i
if %errorlevel% neq 0 goto ziperror
cd..\
del "%file%-ramdisk.lzo"
cd..\
goto end
:ziperror
goto end
:noinput
:end
move boot\ramdisk\*file_contexts* "%~dp0project"
rd /s/q boot
del /q "%~dp0boot.img"