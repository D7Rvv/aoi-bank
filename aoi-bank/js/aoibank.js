document.addEventListener('DOMContentLoaded', function () {
    new Splide('#main-slider', {
        type: 'loop',
        autoplay: true,
        interval: 5000,
        pagination: true,
        arrows: false,
    }).mount();
});

$(function() {
  $('.hamburger').click(function() {
    // メニューの開閉状態を切り替える
    $('.menu').toggleClass('open');

    // ハンバーガーボタンのアクティブクラスを切り替えて三本線をバツにする
    $(this).toggleClass('active');
  });
});