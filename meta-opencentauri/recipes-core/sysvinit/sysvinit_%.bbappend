do_install:append() {
    sed -i 's|PSPLASH_FIFO_DIR=/mnt|PSPLASH_FIFO_DIR=/run|' ${D}${sysconfdir}/default/rcS
}
