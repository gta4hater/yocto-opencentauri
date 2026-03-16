require opencentauri-image-base.bb
inherit extract-partition

DESCRIPTION = "OpenCentauri eMMC Image"
LICENSE = "GPL-3.0-only"

IMAGE_FSTYPES += "wic squashfs"

IMAGE_FEATURES += "read-only-rootfs"

WKS_FILES = "opencentauri-mmc-image.wks.in"
WKS_FILE_DEPENDS += "squashfs-tools-native"

EXTRACT_PARTITION_LABELS = "bootA bootlogos"
