var gulp = require('gulp');
var clean = require('gulp-clean');
var inject = require('gulp-inject');
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');

gulp.task('clean', function() { // remove 'dist' folder
    return gulp.src(['./dist', './assets'], {read: false})
		.pipe(clean());
});

gulp.task('dist-index', ['clean'], function(){ // move index.html to the 'dist' folder
  return gulp.src(['./src/index.html'])
                .pipe(gulp.dest('./dist/'));
});


gulp.task('dist-assets', ['dist-index'], function () { // Move assets to the 'dist' folder
  return gulp.src(['./src/**/**/*.js', './src/**/**/*.css'])
                .pipe(gulp.dest('./assets'));
});

gulp.task('concat', ['dist-index'], function() { // Concat the .js files in the assets folder 
  return gulp.src(['./assets/**/jquery.js', './assets/**/**/*.js'])
    .pipe(concat('all.js'))
    .pipe(gulp.dest('./dist/'));
});

gulp.task('styles', ['concat'], function() { // Move .css files to /dist
  return gulp.src('./assets/**/**/*.css')
    .pipe(gulp.dest('./dist/'));
});

gulp.task('uglify', ['styles'], function(){
  return gulp.src('./dist/all.js')
                .pipe(uglify())
                .pipe(gulp.dest('dist'));
});

gulp.task('inject', ['dist-assets'], function () {
  var target = gulp.src('./dist/index.html');
  // It's not necessary to read the files (will speed up things), we're only after their paths: 
  var sources = gulp.src(['./assets/js/jquery.min.js', './assets/bootstrap/js/bootstrap.min.js', './assets/bootstrap/css/bootstrap.min.css', './assets/bootstrap/css/bootstrap-theme.min.css', './assets/js/*.js', './assets/css/*.css'] , {read: false});
  return target.pipe(inject(sources, {ignorePath: '/dist'}))
    .pipe(gulp.dest('./dist'));
});


gulp.task('default', ['clean', 'dist-index', 'dist-assets', 'inject']);
