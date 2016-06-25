// Set brite.js to load on demand
brite.viewDefaultConfig.loadTmpl = true;
brite.viewDefaultConfig.loadCss = true;


// on document ready, display MainView
$(document).ready(function(){
	$("body").addClass(brite.ua.cssHasNo());
  brite.display("MainView","#pageBody");
});

// Handlebars.js render wrapper
Handlebars.templates = Handlebars.templates || {};	
function render(templateName,data){
	var tmpl = Handlebars.templates[templateName];
	
	if (!tmpl){
		var tmplContent = $("#" + templateName).html();
		tmpl = Handlebars.compile(tmplContent);
		Handlebars.templates[templateName] = tmpl;		
	}
	return tmpl(data).trim();
}

// Just a little indirection to render a template using handlebars.
// This simple indirection allows much flexibility later one, 
// when using pre-compiling or other templating engine are needed.
Handlebars.templates = Handlebars.templates || {};  
function render(templateName,data){
  var tmpl = Handlebars.templates[templateName];
  if (!tmpl){
    tmpl = Handlebars.compile($("#" + templateName).html());
    Handlebars.templates[templateName] = tmpl;
  }
  return tmpl(data);
}

