brite.registerView("MainView",{emptyParent:true},{
  
  create: function(){
    // since this first view is static, no need to call js render, just a .html() on the template.
    return render("tmpl-MainView");
  },
  
  postDisplay: function(){
      var view =this;
      brite.display("ProjectListNav", view.$el.find(".MainView-left"));
      brite.display("ProjectView", view.$el.find(".MainView-projectViewPanel"));
  }
});