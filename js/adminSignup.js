document.getElementById('signup-form').addEventListener('submit', 
    async function(e){
        e.preventDefault();

        const formData = {
            name: e.target.name.value,
            email: e.target.email.value,
            phone: e.target.phone.value,
            password: e.target.password.value
        };

        try {
            const response = await fetch('http://localhost:8009/admin/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });
    
            // Check if response is OK
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
    
            // Determine the content type of the response
            const contentType = response.headers.get('Content-Type');
            
            let result;
            if (contentType && contentType.includes('application/json')) {
                result = await response.json(); // Parse as JSON if content type is JSON
            } else {
                result = await response.text(); // Otherwise parse as text
            }
    
            // Show success message using ToastifyJS
        Toastify({
            text: typeof result === 'string' ? result : 'You have created an account successfully!',
            duration: 3000,
            close: true,
            gravity: "top", // `top` or `bottom`
            position: "center", // `left`, `center` or `right`
            backgroundColor: "green",
        }).showToast();
            
            window.location.href = 'dashboard.html';
        } catch (error) {
            console.error('Error signing up:', error);
            document.getElementById('error-message').innerText = "Password must contain at least 8 characters, including at least one digit, one lowercase and uppercase letter, one special character, and no whitespace.";
        }
    }
);