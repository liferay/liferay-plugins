<div>
	<b>Hello PHP users!</b>
</div>

<br />

Post parameters: <?php print_r($_POST); ?>

<br /><br />

Get parameters: <?php print_r($_GET); ?>

<br /><br />

<form action="index.php" method="post">

Foo: <input name="foo" type="text" value="<?php echo $_GET["foo"]?$_GET["foo"]:$_POST["foo"]; ?>">

<input type="submit" value="Submit" />

</form>

<?php include("navigation.php"); ?>