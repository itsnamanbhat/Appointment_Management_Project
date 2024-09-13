// src/utils/downloadUtils.js

export const downloadAppointmentHtmlFile = (appointment, testResults) => {
    if (!appointment) {
        console.error('Appointment Details not found');
        return;
    }

    // Generate HTML content with table format for appointment details and test results
    const testResultsHtml = testResults.map(test => `
        <tr>
            <td>${test.testName}</td>
        
        </tr>
    `).join('');

    const content = `<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Appointment Details</h2>
        <table>
            <tr>
                <th>Field</th>
                <th>Value</th>
            </tr>
            <tr>
                <td>Appointment ID</td>
                <td>${appointment.id}</td>
            </tr>
            <tr>
                <td>Appointment ID</td>
                <td>SUCCESS</td>
            <tr>
                <td>Appointment Date</td>
                <td>${new Date(appointment.appointmentDate).toLocaleDateString()}</td>
            </tr>
            <tr>
                <td>Patient Name</td>
                <td>${appointment.patient.name}</td>
            </tr>
            <tr>
                <td>Patient Mobile Number</td>
                <td>${appointment.patient.phoneNo}</td>
            </tr>
            <tr>
                <td>Patient Aadhar Number</td>
                <td>${appointment.patient.aadharNumber}</td>
            </tr>
            <tr>
                <td>Patient Age</td>
                <td>${appointment.patient.age}</td>
            </tr>
            <tr>
                <td>Patient Gender</td>
                <td>${appointment.patient.gender}</td>
            </tr>
            <tr>
                <td>Diagnostic Center Name</td>
                <td>${appointment.diagnosticCenter.name}</td>
            </tr>
            <tr>
                <td>Diagnostic Center Address</td>
                <td>${appointment.diagnosticCenter.address}</td>
            </tr>
            <tr>
                <td>Diagnostic Center Contact Number</td>
                <td>${appointment.diagnosticCenter.contactNO}</td>
            </tr>
           
        </table>

        <h2>Test Results</h2>
        <table>
            <tr>
                <th>Test Name</th>
                <th>Test Reading</th>
                <th>Test Condition</th>
            </tr>
            ${testResultsHtml}
        </table>
    </div>
</body>
</html>`;

    // Create a Blob with the HTML content
    const blob = new Blob([content], { type: 'text/html' });

    // Create a link element and trigger the download
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = 'appointmentDetails.html';
    document.body.appendChild(link); // Append link to body to ensure it works in Firefox
    link.click();
    document.body.removeChild(link); // Clean up
};
