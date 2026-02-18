SUMMARY = "Mainsail - Web Interface for Klipper"
DESCRIPTION = "Mainsail is the popular web interface for managing and \
    controlling 3D printers with Klipper."
HOMEPAGE = "https://github.com/mainsail-crew/mainsail"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://github.com/mainsail-crew/mainsail.git;protocol=https;branch=develop \
    file://mainsail-nginx"
SRCREV = "3783e07c6f8f2a1293be92440a52c60e13e50dd3"

S = "${WORKDIR}/git"

RDEPENDS:${PN} = " \
    nginx \
    moonraker \
"

do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
    # Install static web files
    install -d ${D}/var/www/mainsail
    cp -r ${S}/* ${D}/var/www/mainsail/

    # Install nginx site config
    install -d ${D}${sysconfdir}/nginx/sites-available
    install -d ${D}${sysconfdir}/nginx/sites-enabled

    cp ${WORKDIR}/mainsail-nginx ${D}${sysconfdir}/nginx/sites-available/mainsail

    # Symlink to sites-enabled
    ln -sf ${sysconfdir}/nginx/sites-available/mainsail \
        ${D}${sysconfdir}/nginx/sites-enabled/mainsail
}

FILES:${PN} = " \
    /var/www/mainsail \
    ${sysconfdir}/nginx/sites-available/mainsail \
    ${sysconfdir}/nginx/sites-enabled/mainsail \
"

CONFFILES:${PN} = "${sysconfdir}/nginx/sites-available/mainsail"
