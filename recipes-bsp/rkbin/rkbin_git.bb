inherit deploy

DESCRIPTION = "Rockchip-Radxa binary loader"

LICENSE = "BINARY"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=564e729dd65db6f65f911ce0cd340cf9"
NO_GENERIC_LICENSE[BINARY] = "LICENSE.TXT"

SRC_URI = "git://github.com/nishantpoorswani/rkbin.git;branch=radxa;"
SRCREV = "12a5a84d4c5c3d18bf22e3c44cc998d98c1b6994"
S = "${WORKDIR}/git"

LOADER_rk3308 ?= "bin/rk33/rk3308_loader_589MHz_uart2_m0_v1.26.111.bin"
MINILOADER_rk3308 ?= "bin/rk33/rk3308_miniloader_v1.14.bin"
DDR_rk3308 ?= "bin/rk33/rk3308_ddr_589MHz_uart2_m0_v1.26.bin"

do_configure[noexec] = "1"

DDR_BIN = "ddr.bin"
LOADER_BIN = "loader.bin"
MINILOADER_BIN = "miniloader.bin"
ATF_BIN = "atf.bin"
UBOOT_IMG = "uboot.img"
TRUST_IMG = "trust.img"

RKBINARY_DEPLOY_DIR = "${DEPLOYDIR}/radxa-binary"

do_deploy () {
	install -d ${RKBINARY_DEPLOY_DIR}
	echo "done"
	[ ${DDR} ] && cp ${S}/${DDR} ${RKBINARY_DEPLOY_DIR}/${DDR_BIN}
	echo "done"	
	[ ${MINILOADER} ] && cp ${S}/${MINILOADER} ${RKBINARY_DEPLOY_DIR}/${MINILOADER_BIN}
	echo "done"	
	[ ${LOADER} ] && cp ${S}/${LOADER} ${RKBINARY_DEPLOY_DIR}/${LOADER_BIN}
	echo "done"
	[ ${ATF} ] && cp ${S}/${ATF} ${RKBINARY_DEPLOY_DIR}/${ATF_BIN}

	# Don't remove it!
	echo "done"
}

deploy_prebuilt_image () {
	install -d ${RKBINARY_DEPLOY_DIR}
	echo "done"
	[ -e {S}/img/${UBOOT_IMG} ] && \
		cp ${S}/img/${UBOOT_IMG} ${RKBINARY_DEPLOY_DIR}/${UBOOT_IMG}
	echo "done"
	[ -e ${S}/img/${TRUST_IMG}  ] && \
		cp ${S}/img/${TRUST_IMG} ${RKBINARY_DEPLOY_DIR}/${TRUST_IMG}
}

do_deploy_append_rk3328 () {
	deploy_prebuilt_image
}

do_deploy_append_rk3399 () {
	deploy_prebuilt_image
}

do_deploy_append_rk3308 () {
	deploy_prebuilt_image
}

addtask deploy before do_build after do_compile

do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_package_write_tar[noexec] = "1"
