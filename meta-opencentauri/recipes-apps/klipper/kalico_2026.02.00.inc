HOMEPAGE = "https://kalico.gg/"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=1ebbd3e34237af26da5dc08a4e440464"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://github.com/OpenCentauri/kalico.git;protocol=https;branch=rpmsg"
SRCREV = "0d0f76e273961eac1068117e23ea1da89dc60da6"

S = "${WORKDIR}/git"

DEPENDS = " \
    python3-native \
"
