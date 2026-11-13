// CampusGig Client-side Script
document.addEventListener('DOMContentLoaded', () => {
    // Mobile navigation toggle
    const mobileMenuButton = document.getElementById('mobile-menu-button');
    const mobileMenu = document.getElementById('mobile-menu');

    if (mobileMenuButton && mobileMenu) {
        mobileMenuButton.addEventListener('click', () => {
            mobileMenu.classList.toggle('hidden');
        });
    }

    // Auto-dismiss alerts after 5 seconds
    const alerts = document.querySelectorAll('.auto-dismiss-alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.transition = 'opacity 0.5s ease';
            alert.style.opacity = '0';
            setTimeout(() => alert.remove(), 500);
        }, 5000);
    });
});

// Quick fill helper for demo accounts on the login page
function fillDemoCredentials(email, role) {
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    if (emailInput && passwordInput) {
        emailInput.value = email;
        passwordInput.value = 'password123';
        
        // Visual indicator
        emailInput.classList.add('ring-2', 'ring-indigo-500');
        passwordInput.classList.add('ring-2', 'ring-indigo-500');
        setTimeout(() => {
            emailInput.classList.remove('ring-2', 'ring-indigo-500');
            passwordInput.classList.remove('ring-2', 'ring-indigo-500');
        }, 1000);
    }
}
