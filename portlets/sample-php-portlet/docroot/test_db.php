<?php

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;

$url = PropsUtil::get("jdbc.default.url");

$dbType = StringUtil::split($url, ":")[1];
$host = StringUtil::split($url, "/")[2];

$username = PropsUtil::get("jdbc.default.username");
$password = PropsUtil::get("jdbc.default.password");

if ($dbType == DB::TYPE_DB2) {
	$link = db2_connect($host, $username, $password);
}
elseif ($dbType == DB::TYPE_DERBY) {
	$link = db2_connect($host, $username, $password);
}
elseif ($dbType == DB::TYPE_FIREBIRD) {
	$link = ibase_connect($host, $username, $password);
}
elseif ($dbType == DB::TYPE_INFORMIX) {
	$link = ifx_connect($host, $username, $password);
}
elseif ($dbType == DB::TYPE_INGRES) {
	$link = ingres_connect($host, $username, $password);
}
elseif ($dbType == DB::TYPE_INTERBASE) {
	$link = ibase_connect($host, $username, $password);
}
elseif ($dbType == DB::TYPE_MYSQL) {
	$link = mysql_connect($host, $username, $password);
}
elseif ($dbType == DB::TYPE_ORACLE) {
	$link = oci_connect($host, $username, $password);
}
elseif ($dbType == DB::TYPE_POSTGRESQL) {
	$link = pg_connect($host, $username, $password);
}
elseif ($dbType == DB::TYPE_SQLSERVER) {
	$link = mssql_connect($host, $username, $password);
}
elseif ($dbType == DB::TYPE_SYBASE) {
	$link = sybase_connect($host, $username, $password);
}

if ($link) {
	echo "Successfully connected to the $dbType database.";
}
else {
	echo "Unable to connect to the $dbType database.";
}

?>

<?php include("navigation.php"); ?>