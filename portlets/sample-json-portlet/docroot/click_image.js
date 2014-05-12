toString : function() {
	var main = document.createElement('div');
	var clickMe = document.createElement('span');
	var id;

	if (this.id) {
		id = this.id
	}
	else {
		date = new Date();
		id = '' + date.getTime();
	}

	clickMe.innerHTML = 'Click me.';
	clickMe.setAttribute('onclick', 'document.getElementById("' + id + 'image").style.display = "inline"; this.style.display = "none"');
	clickMe.id = id + 'link';
	clickMe.style.cursor = 'pointer';

	var image = document.createElement('img');

	image.src = 'http://www.liferay.com/image/company_logo?img_id=liferay.com';
	image.setAttribute('onclick', 'document.getElementById("' + id + 'link").style.display = "inline"; this.style.display = "none"');
	image.style.cursor = 'pointer';
	image.id = id + 'image';
	image.style.display = 'none';

	main.appendChild(clickMe);
	main.appendChild(document.createElement('br'));
	main.appendChild(image);

	return(main.innerHTML);
}