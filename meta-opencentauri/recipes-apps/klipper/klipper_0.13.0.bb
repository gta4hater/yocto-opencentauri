SUMMARY = "Klipper 3D Printer Firmware"
DESCRIPTION = "Klipper is a 3D printer firmware that combines the power of a general purpose computer with one or more micro-controllers."
HOMEPAGE = "https://www.klipper3d.org/"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=1ebbd3e34237af26da5dc08a4e440464"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://github.com/Klipper3d/klipper.git;protocol=https;branch=master \
    file://klipper-init-d"
SRCREV = "61c0c8d2ef40340781835dd53fb04cc7a454e37a"

S = "${WORKDIR}/git"

inherit python3-dir useradd update-rc.d

DEPENDS = " \
    python3-native \
"

RDEPENDS:${PN} = " \
    python3 \
    python3-cffi \
    python3-greenlet \
    python3-jinja2 \
    python3-markupsafe \
    python3-pyserial \
    python3-numpy \
    python3-can \
    python3-msgspec \
"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "-r klipper"
USERADD_PARAM:${PN} = " \
    --system \
    --home-dir /var/lib/klipper \
    --no-create-home \
    --shell /bin/false \
    --gid klipper \
    klipper \
"

INITSCRIPT_NAME = "klipper"
INITSCRIPT_PARAMS = "defaults 95 5"

do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
    # Install klipper python package
    install -d ${D}${datadir}/klipper
    cp -r ${S}/klippy ${D}${datadir}/klipper/

    # Config directory
    install -d ${D}${sysconfdir}/klipper

    # Install SysVinit script
    install -d ${D}${sysconfdir}/init.d
    cp -r ${WORKDIR}/klipper-init-d ${D}${sysconfdir}/init.d/klipper
    chmod 0755 ${D}${sysconfdir}/init.d/klipper
}

FILES:${PN} = " \
    ${datadir}/klipper \
    ${sysconfdir}/init.d/klipper \
    ${sysconfdir}/klipper \
"

CONFFILES:${PN} = "${sysconfdir}/klipper/printer.cfg"
