# /bin/sh

usage () {
cat << EOF
Usage: $(basename $0) [login_ensimag]
EOF
}

if [ "$#" -lt 1 ]; then
	echo "Login manquant"
	usage
	exit 1
fi

ssh -n -f -N -L 1521:ensibm.imag.fr:1521 $1@ensibm.imag.fr

