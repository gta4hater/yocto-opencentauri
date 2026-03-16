require opencentauri-image-base.bb

DESCRIPTION = "OpenCentauri eMMC Image"
LICENSE = "GPL-3.0-only"

IMAGE_FEATURES += "package-management"

WKS_FILES = "opencentauri-usb-image.wks.in"
