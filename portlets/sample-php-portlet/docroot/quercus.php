<!--
<?php

function quercus_test() {
	return function_exists("resin_call_stack");
}

?>
-->

<style type="text/css">
	.message {
		background: #CCCCCC;
		border: 1px solid blue;
		padding: 10px;
	}

	.footer {
	}

	#failure {
		<?php echo "display: none;"; ?>
	}

	#failure_default_interpreter {
		display: none;
		<?php if (!quercus_test()) echo "display: block;"; ?>
	}

	#success {
		display: none;
		<?php if (quercus_test()) echo "display: block;"; ?>
	}
</style>

<div>
	Testing for Quercus&#153;...
</div>

<br />

<div class="message" id="failure">
	PHP files are not being interpreted by Quercus&#153;.
</div>

<div class="message" id="failure_default_interpreter">
	PHP is being interpreted, but not by Quercus&#153;! Please check your configuration.
</div>

<div class="message" id="success">
	Congratulations! Quercus&#153; seems to be working fine. Have fun!
</div>

<br />

<div>
	Documentation is available at <a href="http://quercus.caucho.com">http://quercus.caucho.com</a>.
</div>

<div class="separator"></div>

<div class="footer">
	Copyright &copy; 1998-2007 <a href="http://www.caucho.com">Caucho Technology, Inc</a>. All rights reserved.<br />
	Resin <sup><font size="-1">&#174;</font></sup> is a registered trademark, and Quercus<sup>tm</sup>, Amber<sup>tm</sup>, and Hessian<sup>tm</sup> are trademarks of Caucho Technology.
</div>

<?php include("navigation.php"); ?>