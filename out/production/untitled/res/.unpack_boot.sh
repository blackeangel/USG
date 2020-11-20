#!/bin/bash
bin="$PWD/bin";
chmod -R 755 "$bin"/*;
cd "$PWD";
cp $PWD/project/boot.img "$PWD/boot.img";
#unpackbootimg=python $bin/unpackbootimg.py;
file=boot.img;
origfile=boot;
filename="${origfile%.*}";
wimage="$file";
if [[ "$wimage" = "boot.img" ]];
then
if [ -f boot.img ];
then
if [ "$file" == boot.img ];
then
cp -f boot.img bckp-boot.img;
else
mv -f boot.img bckp-boot.img;
cp -f "$file" bckp-"$file";
mv -f "$file" boot.img;
fi;
else
cp -f "$file" bckp-"$file";
mv -f "$file" boot.img;
fi;
if [[ $filename != *boot* ]];
then
filename=boot-"$filename";
fi;
fi;
if [ -d "$filename" ];
then
rm -rf "$filename";
fi;
mkdir -p "$filename";
chmod 777 "$filename";
if [ ! "$3" ];
then
#$unpackbootimg -i "$wimage" -o "$filename";
python $bin/unpackbootimg.py -i "$wimage" -o "$filename";
else
#$unpackbootimg -i "$wimage" -o "$filename" --mtk 1;
python $bin/unpackbootimg.py -i "$wimage" -o "$filename";
fi;
if [[ "$wimage" = "boot.img" ]];
then
rm -f boot.img;
if [ "$file" == boot.img ];
then
mv -f bckp-boot.img boot.img;
else
if [ -f bckp-boot.img ];
then
mv -f bckp-boot.img boot.img;
mv -f bckp-"$file" "$file";
else
mv -f bckp-"$file" "$file";
fi;
fi;
fi;
cd "$filename";
comprfile=$(find . -name "*-ramdisk.*")
compress="${comprfile##*.}";
filecomp="$wimage"-ramdisk-compress;
if [ ! -f "$filecomp" ]
then 
echo "$compress" > "$filecomp" ;
fi;
mkdir -p ramdisk;
cd ramdisk;
if [ "$compress" == "gz" ];
then
gzip -dcv "../$wimage-ramdisk.gz" | cpio -i ;
cd ../ ;
rm -f "$wimage-ramdisk.gz";
cd ../ ;
fi;
if [ "$compress" == "lzma" ];
then
xz -dcv "../$wimage-ramdisk.lzma" | cpio -i ;
cd ../ ;
rm -f "$wimage-ramdisk.lzma";
cd ../ ;
fi;
if [ "$compress" == "xz" ];
then
xz -dcv "../$wimage-ramdisk.xz" | cpio -i ;
cd ../ ;
rm -f "$wimage-ramdisk.xz";
cd ../ ;
fi;
if [ "$compress" == "bz2" ];
then
bzip2 -dcv "../$wimage-ramdisk.bz2" | cpio -i ;
cd ../ ;
rm -f "$wimage-ramdisk.bz2";
cd ../ ;
fi;
if [ "$compress" == "lzo" ];
then
lzop -dcv "../$wimage-ramdisk.lzo" | cpio -i ;
cd ../ ;
rm -f "$wimage-ramdisk.lzo";
cd ../ ;
fi;
if [ "$compress" == "lz4" ];
then
lz4 -dv "../$wimage-ramdisk.lz4" stdout | cpio -i ;
cd ../ ;
rm -f "$wimage-ramdisk.lz4";
cd ../ ;
fi;
mv $PWD/boot/ramdisk/*file_contexts* "$PWD/project"
rm -rf "$PWD/boot"
rm -rf "$PWD/boot.img"