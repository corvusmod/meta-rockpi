DEFAULT_PREFERENCE = "1"

include u-boot-rockpi-s.inc

SRC_URI = " \
	git://github.com/u-boot/u-boot.git \
"
SRCREV = "c3d3bc84bef5ee95d7199c23e1a34e47ea8f0daf"

S = "${WORKDIR}/git"
