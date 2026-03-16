DESCRIPTION = "WebUI Switcher"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"

SRC_URI = " \
    file://webui-switcher.init \
    file://webui-nginx \
"

inherit update-rc.d

INITSCRIPT_NAME = "webui-switcher"
INITSCRIPT_PARAMS = "defaults 90 20"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/webui-switcher.init ${D}${sysconfdir}/init.d/webui-switcher

    # Install nginx site config
    install -d ${D}${sysconfdir}/nginx/sites-available
    install -d ${D}${sysconfdir}/nginx/sites-enabled

    cp ${WORKDIR}/webui-nginx ${D}${sysconfdir}/nginx/sites-available/webui

    # Symlink to sites-enabled
    ln -sf ${sysconfdir}/nginx/sites-available/webui \
        ${D}${sysconfdir}/nginx/sites-enabled/webui
}

FILES_${PN} += " \
    ${sysconfdir}/init.d/webui-switcher \
    ${sysconfdir}/nginx/sites-available/webui \
    ${sysconfdir}/nginx/sites-enabled/webui \
"
