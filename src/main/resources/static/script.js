document.addEventListener('DOMContentLoaded', () => {

    const darkModeToggle = document.getElementById('darkModeToggle');
    const body = document.body;

    const savedTheme = localStorage.getItem('theme');

    if (savedTheme === 'dark') {
        body.classList.add('dark-mode');
        body.classList.remove('light-mode');
        if (darkModeToggle) {
            darkModeToggle.checked = true;
        }
    } else {
        body.classList.add('light-mode');
        body.classList.remove('dark-mode');
        if (darkModeToggle) {
            darkModeToggle.checked = false;
        }
    }

    if (darkModeToggle) {
        darkModeToggle.addEventListener('change', () => {
            body.classList.toggle('dark-mode');
            body.classList.toggle('light-mode');

            if (body.classList.contains('dark-mode')) {
                localStorage.setItem('theme', 'dark');
            } else {
                localStorage.setItem('theme', 'light');
            }
        });
    }

    const markPaidCheckbox = document.getElementById('markPaid');
        const paymentDateGroup = document.getElementById('paymentDateGroup'); // <--- NEW: Target the wrapper div
        const paymentDateInput = document.getElementById('paymentDate');     // <--- NEW: Still target the input

        if (markPaidCheckbox && paymentDateGroup && paymentDateInput) { // <--- Updated condition
            // Function to toggle visibility
            function togglePaymentDateVisibility() {
                if (markPaidCheckbox.checked) {
                    paymentDateGroup.style.display = 'block'; // Or 'flex', 'grid' depending on your CSS
                    paymentDateInput.setAttribute('required', 'required'); // Make it required when visible
                } else {
                    paymentDateGroup.style.display = 'none';
                    paymentDateInput.removeAttribute('required'); // Remove required when hidden
                    paymentDateInput.value = ''; // Clear the value when hidden
                }
            }

            // Set initial state when the page loads (important for Thymeleaf pre-filled forms)
            togglePaymentDateVisibility();

            // Add event listener for changes to the checkbox
            markPaidCheckbox.addEventListener('change', togglePaymentDateVisibility);
        }

    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const username = document.getElementById('loginUsername').value;
            const password = document.getElementById('loginPassword').value;
            const error = document.getElementById('loginError');
            if (username !== 'admin' || password !== 'admin') {
                error.textContent = 'Invalid credentials';
            } else {
                error.textContent = '';
                window.location.href = 'home.html';
            }
        });
    }

    const registerForm = document.getElementById('registerForm');
    const passwordField = document.getElementById('password');
    const confirmPasswordField = document.getElementById('confirmPassword');
    const registerError = document.getElementById('registerError');

    if (registerForm) {
        registerForm.addEventListener('submit', function(e) {
            e.preventDefault();

            const fullname = document.getElementById('fullname')?.value.trim();
            const email = document.getElementById('email')?.value.trim();
            const mobile = document.getElementById('mobile')?.value.trim();
            const usernameField = document.getElementById('username');
            const username = usernameField ? usernameField.value.trim() : '';
            const dob = document.getElementById('dob')?.value.trim();
            const password = passwordField.value.trim();
            const confirmPassword = confirmPasswordField.value.trim();


            if (!fullname || !email || !mobile || !username || !dob || !password || !confirmPassword) {
                registerError.textContent = 'All fields are required.';
                registerError.style.display = 'block';
                return;
            }
            if (password !== confirmPassword) {
                registerError.textContent = 'Passwords do not match.';
                registerError.style.display = 'block';
                return;
            }
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email)) {
                registerError.textContent = 'Please enter a valid email address.';
                registerError.style.display = 'block';
                return;
            }
            const mobileRegex = /^\d{10}$/;
            if (!mobileRegex.test(mobile)) {
                registerError.textContent = 'Please enter a valid 10-digit mobile number.';
                registerError.style.display = 'block';
                return;
            }

            if (username === 'existinguser' || email === 'existing@email.com') {
                registerError.textContent = 'Username or Email already exists.';
                registerError.style.display = 'block';
                return;
            }

            registerError.textContent = '';
            registerError.style.display = 'none';

            alert('Registration successful!');
            window.location.href = 'index.html';
        });
    }
});

function generatePDF() {
    alert('PDF generation is currently a placeholder.');
}