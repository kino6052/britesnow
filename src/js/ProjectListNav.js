brite.registerView("ProjectListNav", {
   create: function(){
       return render("tmpl-ProjectListNav", {projects: main.projectListTestData});
   } 
});