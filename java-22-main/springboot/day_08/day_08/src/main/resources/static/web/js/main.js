toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-right",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}
const activeMenu = () => {
    const pathName = window.location.pathname;
    console.log(pathName)

    const menuItems = document.querySelectorAll('.menu-item');
    menuItems.forEach( menuItem => {
            if (menuItem.getAttribute('href') === pathName) {
                menuItem.classList.add('active');
            }
        }

    )
}
activeMenu();
