inherit cargo

SUMMARY = "Atomscreen - Interface for Klipper"
DESCRIPTION = "A native klipper touchscreen grapical user interface that works \
    both with and without a display server such as X11/Wayland "
HOMEPAGE = "https://github.com/OpenCentauri/atomscreen"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "git://github.com/OpenCentauri/atomscreen.git;protocol=https;branch=main"
SRCREV = "66bb8a41018b5f8ca857efb51e64a596a33699c3"

S = "${WORKDIR}/git"
