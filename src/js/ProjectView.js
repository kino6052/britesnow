brite.registerView("ProjectView", {
    create: function(){
        return render("tmpl-ProjectView", {project:main.projectListTestData[0], tasks:main.taskListTestData});
    }
})