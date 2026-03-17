inherit update-rc.d

SUMMARY = "Grumpy Screen - Native Touch UI for Klipper/Moonraker"
DESCRIPTION = "Grumpy Screen is a native touch UI for 3D printers running \
    Klipper/Moonraker. Built on LVGL as a standalone executable with no \
    dependency on X/Wayland display servers."
HOMEPAGE = "https://github.com/pellcorp/grumpyscreen"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "gitsm://github.com/pellcorp/grumpyscreen.git;protocol=https;branch=main \
    file://grumpyscreen.init \
    file://grumpyscreen.cfg \
    file://0001-Use-printer_data-config-folder.patch \
    file://0001-Don-t-write-to-usr-share.patch \
"
SRCREV = "20a67b1f80a01d287216f4f3123f41d2717056e2"

S = "${WORKDIR}/git"

RDEPENDS:${PN} = " \
    klipper \
    moonraker \
    gui-switcher \
"

INITSCRIPT_NAME = "grumpyscreen"
INITSCRIPT_PARAMS = "disable"

EXTRA_OEMAKE = " \
    CROSS_COMPILE=yocto- \
    CC='${CC}' \
    CXX='${CXX}' \
    AR='${AR}' \
    OBJCOPY='${OBJCOPY}' \
"

do_compile() {
    cd ${S}
    oe_runmake libhv.a
    oe_runmake wpaclient
    oe_runmake default \
            LDFLAGS="-lm -L${S}/libhv/lib \
            -l:libhv.a -latomic -lpthread \
            -L${S}/wpa_supplicant/wpa_supplicant/ -l:libwpa_client.a \
            -lstdc++fs \
            ${LDFLAGS}" \
            GUPPY_SMALL_SCREEN="y" GUPPYSCREEN_VERSION="${PV}"
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/build/bin/guppyscreen ${D}${bindir}/grumpyscreen

    install -d ${D}${datadir}/grumpyscreen/themes
    if [ -d ${S}/themes ]; then
        cp -r ${S}/themes/* ${D}${datadir}/grumpyscreen/themes/
    fi

    install -d ${D}${sysconfdir}/grumpyscreen
    install -m 0644 ${WORKDIR}/grumpyscreen.cfg ${D}${sysconfdir}/grumpyscreen/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/grumpyscreen.init ${D}${sysconfdir}/init.d/grumpyscreen
}

FILES:${PN} = " \
    ${bindir}/grumpyscreen \
    ${datadir}/grumpyscreen \
    ${sysconfdir}/grumpyscreen \
    ${sysconfdir}/init.d/grumpyscreen \
"

CONFFILES:${PN} = "${sysconfdir}/grumpyscreen/grumpyscreen.cfg"
