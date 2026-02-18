SUMMARY = "Read metadata from Python packages"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PYPI_PACKAGE = "importlib_metadata"

inherit pypi python_setuptools_build_meta
SRC_URI[sha256sum] = "509ecb2ab77071db5137c655e24ceb3eee66e7bbc6574165d0d114d9fc4bbe68"

DEPENDS += " \
    python3-setuptools-scm-native \
    python3-setuptools-native \
    python3-wheel-native \
"
