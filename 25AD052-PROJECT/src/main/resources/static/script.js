const BASE_URL = "/api";


// ======================================================
// INSTALLATION
// ======================================================

// ADD INSTALLATION
async function addInstallation() {

    const name =
        document.getElementById("installationName").value;

    const location =
        document.getElementById("installationLocation").value;

    const totalCapacity =
        document.getElementById("installationCapacity").value;

    if (!name || !location || !totalCapacity) {
        alert("Please fill all installation fields.");
        return;
    }

    const installation = {
        name: name,
        location: location,
        totalCapacity: Number(totalCapacity)
    };

    try {

        const response = await fetch(
            `${BASE_URL}/installations`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(installation)
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Installation added successfully!");

        document.getElementById("installationName").value = "";
        document.getElementById("installationLocation").value = "";
        document.getElementById("installationCapacity").value = "";

        loadInstallations();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error adding installation: " + error.message);
    }
}


// GET ALL INSTALLATIONS
async function loadInstallations() {

    try {

        const response =
            await fetch(`${BASE_URL}/installations`);

        if (!response.ok) {
            throw new Error("Failed to load installations");
        }

        const installations =
            await response.json();

        const container =
            document.getElementById("installationList");

        container.innerHTML = "";

        installations.forEach(installation => {

            const card =
                document.createElement("div");

            card.className = "result-card";

            card.innerHTML = `
                <h3>${installation.name}</h3>

                <p>
                    <strong>ID:</strong>
                    ${installation.id}
                </p>

                <p>
                    <strong>Location:</strong>
                    ${installation.location}
                </p>

                <p>
                    <strong>Capacity:</strong>
                    ${installation.totalCapacity}
                </p>

                <button onclick="updateInstallation(${installation.id})">
                    Update
                </button>

                <button onclick="deleteInstallation(${installation.id})">
                    Delete
                </button>
            `;

            container.appendChild(card);
        });

    } catch (error) {

        console.error(error);

        document.getElementById("installationList").innerHTML =
            "<p>Unable to load installations.</p>";
    }
}


// UPDATE INSTALLATION
async function updateInstallation(id) {

    const name =
        prompt("Enter installation name:");

    if (name === null) return;

    const location =
        prompt("Enter installation location:");

    if (location === null) return;

    const capacity =
        prompt("Enter total capacity:");

    if (capacity === null) return;

    const installation = {
        name: name,
        location: location,
        totalCapacity: Number(capacity)
    };

    try {

        const response = await fetch(
            `${BASE_URL}/installations/${id}`,
            {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(installation)
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Installation updated successfully!");

        loadInstallations();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error updating installation: " + error.message);
    }
}


// DELETE INSTALLATION
async function deleteInstallation(id) {

    if (!confirm(
        "Are you sure you want to delete this installation?"
    )) {
        return;
    }

    try {

        const response = await fetch(
            `${BASE_URL}/installations/${id}`,
            {
                method: "DELETE"
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Installation deleted successfully!");

        loadInstallations();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error deleting installation: " + error.message);
    }
}


// ======================================================
// HOUSEHOLD
// ======================================================

// ADD HOUSEHOLD
async function addHousehold() {

    const householdName =
        document.getElementById("householdName").value;

    const houseNumber =
        document.getElementById("houseNumber").value;

    const allocationRatio =
        document.getElementById("allocationRatio").value;

    if (!householdName || !houseNumber || !allocationRatio) {
        alert("Please fill all household fields.");
        return;
    }

    const household = {
        householdName: householdName,
        houseNumber: houseNumber,
        allocationRatio: Number(allocationRatio)
    };

    try {

        const response = await fetch(
            `${BASE_URL}/households`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(household)
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Household added successfully!");

        document.getElementById("householdName").value = "";
        document.getElementById("houseNumber").value = "";
        document.getElementById("allocationRatio").value = "";

        loadHouseholds();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error adding household: " + error.message);
    }
}


// GET ALL HOUSEHOLDS
async function loadHouseholds() {

    try {

        const response =
            await fetch(`${BASE_URL}/households`);

        if (!response.ok) {
            throw new Error("Failed to load households");
        }

        const households =
            await response.json();

        const container =
            document.getElementById("householdList");

        container.innerHTML = "";

        households.forEach(household => {

            const card =
                document.createElement("div");

            card.className = "result-card";

            card.innerHTML = `
                <h3>${household.householdName}</h3>

                <p>
                    <strong>ID:</strong>
                    ${household.id}
                </p>

                <p>
                    <strong>House Number:</strong>
                    ${household.houseNumber}
                </p>

                <p>
                    <strong>Allocation Ratio:</strong>
                    ${household.allocationRatio}
                </p>

                <button onclick="updateHousehold(${household.id})">
                    Update
                </button>

                <button onclick="deleteHousehold(${household.id})">
                    Delete
                </button>
            `;

            container.appendChild(card);
        });

    } catch (error) {

        console.error(error);

        document.getElementById("householdList").innerHTML =
            "<p>Unable to load households.</p>";
    }
}


// UPDATE HOUSEHOLD
async function updateHousehold(id) {

    const householdName =
        prompt("Enter household name:");

    if (householdName === null) return;

    const houseNumber =
        prompt("Enter house number:");

    if (houseNumber === null) return;

    const allocationRatio =
        prompt("Enter allocation ratio:");

    if (allocationRatio === null) return;

    const household = {
        householdName: householdName,
        houseNumber: houseNumber,
        allocationRatio: Number(allocationRatio)
    };

    try {

        const response = await fetch(
            `${BASE_URL}/households/${id}`,
            {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(household)
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Household updated successfully!");

        loadHouseholds();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error updating household: " + error.message);
    }
}


// DELETE HOUSEHOLD
async function deleteHousehold(id) {

    if (!confirm(
        "Are you sure you want to delete this household?"
    )) {
        return;
    }

    try {

        const response = await fetch(
            `${BASE_URL}/households/${id}`,
            {
                method: "DELETE"
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Household deleted successfully!");

        loadHouseholds();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error deleting household: " + error.message);
    }
}


// ======================================================
// GENERATION LOG
// ======================================================

// ADD GENERATION
async function addGeneration() {

    const date =
        document.getElementById("generationDate").value;

    const unitsGenerated =
        document.getElementById("unitsGenerated").value;

    if (!date || !unitsGenerated) {
        alert("Please enter date and units generated.");
        return;
    }

    const generation = {
        date: date,
        unitsGenerated: Number(unitsGenerated)
    };

    try {

        const response = await fetch(
            `${BASE_URL}/generation-logs`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(generation)
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Generation log added successfully!");

        document.getElementById("generationDate").value = "";
        document.getElementById("unitsGenerated").value = "";

        loadGenerations();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error adding generation log: " + error.message);
    }
}


// GET ALL GENERATION LOGS
async function loadGenerations() {

    try {

        const response =
            await fetch(`${BASE_URL}/generation-logs`);

        if (!response.ok) {
            throw new Error("Failed to load generation logs");
        }

        const generations =
            await response.json();

        const container =
            document.getElementById("generationList");

        container.innerHTML = "";

        generations.forEach(generation => {

            const card =
                document.createElement("div");

            card.className = "result-card";

            card.innerHTML = `
                <h3>Generation Log</h3>

                <p>
                    <strong>ID:</strong>
                    ${generation.id}
                </p>

                <p>
                    <strong>Date:</strong>
                    ${generation.date}
                </p>

                <p>
                    <strong>Units Generated:</strong>
                    ${generation.unitsGenerated}
                </p>

                <button onclick="updateGeneration(${generation.id})">
                    Update
                </button>

                <button onclick="deleteGeneration(${generation.id})">
                    Delete
                </button>
            `;

            container.appendChild(card);
        });

    } catch (error) {

        console.error(error);

        document.getElementById("generationList").innerHTML =
            "<p>Unable to load generation logs.</p>";
    }
}


// UPDATE GENERATION
async function updateGeneration(id) {

    const date =
        prompt("Enter generation date (YYYY-MM-DD):");

    if (date === null) return;

    const unitsGenerated =
        prompt("Enter units generated:");

    if (unitsGenerated === null) return;

    const generation = {
        date: date,
        unitsGenerated: Number(unitsGenerated)
    };

    try {

        const response = await fetch(
            `${BASE_URL}/generation-logs/${id}`,
            {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(generation)
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Generation log updated successfully!");

        loadGenerations();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error updating generation: " + error.message);
    }
}


// DELETE GENERATION
async function deleteGeneration(id) {

    if (!confirm(
        "Are you sure you want to delete this generation log?"
    )) {
        return;
    }

    try {

        const response = await fetch(
            `${BASE_URL}/generation-logs/${id}`,
            {
                method: "DELETE"
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Generation log deleted successfully!");

        loadGenerations();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error deleting generation log: " + error.message);
    }
}


// ======================================================
// CONSUMPTION LOG
// ======================================================

// ADD CONSUMPTION
async function addConsumption() {

    const householdId =
        document.getElementById("consumptionHouseholdId").value;

    const date =
        document.getElementById("consumptionDate").value;

    const unitsConsumed =
        document.getElementById("unitsConsumed").value;

    if (!householdId || !date || !unitsConsumed) {
        alert("Please fill all consumption fields.");
        return;
    }

    const consumption = {
        householdId: Number(householdId),
        date: date,
        unitsConsumed: Number(unitsConsumed)
    };

    try {

        const response = await fetch(
            `${BASE_URL}/consumption-logs`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(consumption)
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Consumption log added successfully!");

        document.getElementById("consumptionHouseholdId").value = "";
        document.getElementById("consumptionDate").value = "";
        document.getElementById("unitsConsumed").value = "";

        loadConsumptions();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error adding consumption log: " + error.message);
    }
}


// GET ALL CONSUMPTION LOGS
async function loadConsumptions() {

    try {

        const response =
            await fetch(`${BASE_URL}/consumption-logs`);

        if (!response.ok) {
            throw new Error("Failed to load consumption logs");
        }

        const consumptions =
            await response.json();

        const container =
            document.getElementById("consumptionList");

        container.innerHTML = "";

        consumptions.forEach(consumption => {

            const card =
                document.createElement("div");

            card.className = "result-card";

            card.innerHTML = `
                <h3>Consumption Log</h3>

                <p>
                    <strong>ID:</strong>
                    ${consumption.id}
                </p>

                <p>
                    <strong>Household ID:</strong>
                    ${consumption.householdId}
                </p>

                <p>
                    <strong>Date:</strong>
                    ${consumption.date}
                </p>

                <p>
                    <strong>Units Consumed:</strong>
                    ${consumption.unitsConsumed}
                </p>

                <button onclick="updateConsumption(${consumption.id})">
                    Update
                </button>

                <button onclick="deleteConsumption(${consumption.id})">
                    Delete
                </button>
            `;

            container.appendChild(card);
        });

    } catch (error) {

        console.error(error);

        document.getElementById("consumptionList").innerHTML =
            "<p>Unable to load consumption logs.</p>";
    }
}


// UPDATE CONSUMPTION
async function updateConsumption(id) {

    const householdId =
        prompt("Enter household ID:");

    if (householdId === null) return;

    const date =
        prompt("Enter consumption date (YYYY-MM-DD):");

    if (date === null) return;

    const unitsConsumed =
        prompt("Enter units consumed:");

    if (unitsConsumed === null) return;

    const consumption = {
        householdId: Number(householdId),
        date: date,
        unitsConsumed: Number(unitsConsumed)
    };

    try {

        const response = await fetch(
            `${BASE_URL}/consumption-logs/${id}`,
            {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(consumption)
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Consumption log updated successfully!");

        loadConsumptions();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error updating consumption: " + error.message);
    }
}


// DELETE CONSUMPTION
async function deleteConsumption(id) {

    if (!confirm(
        "Are you sure you want to delete this consumption log?"
    )) {
        return;
    }

    try {

        const response = await fetch(
            `${BASE_URL}/consumption-logs/${id}`,
            {
                method: "DELETE"
            }
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        alert("Consumption log deleted successfully!");

        loadConsumptions();
        loadDashboard();

    } catch (error) {

        console.error(error);
        alert("Error deleting consumption: " + error.message);
    }
}


// ======================================================
// MONTHLY SUMMARY
// ======================================================

async function getMonthlySummary() {

    const householdId =
        document.getElementById("summaryHouseholdId").value;

    const year =
        document.getElementById("summaryYear").value;

    const month =
        document.getElementById("summaryMonth").value;

    if (!householdId || !year || !month) {
        alert("Please enter household ID, year and month.");
        return;
    }

    if (month < 1 || month > 12) {
        alert("Month must be between 1 and 12.");
        return;
    }

    try {

        const response = await fetch(
            `${BASE_URL}/solar-share/monthly-summary/${householdId}?year=${year}&month=${month}`
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        const summary =
            await response.json();

        const container =
            document.getElementById("summaryResult");

        container.innerHTML = `

            <div class="result-card">

                <h3>Monthly Summary</h3>

                <p>
                    <strong>Household ID:</strong>
                    ${summary.householdId}
                </p>

                <p>
                    <strong>Year:</strong>
                    ${summary.year}
                </p>

                <p>
                    <strong>Month:</strong>
                    ${summary.month}
                </p>

                <p>
                    <strong>Generation Share:</strong>
                    ${summary.monthlyGenerationShare} units
                </p>

                <p>
                    <strong>Consumption:</strong>
                    ${summary.monthlyConsumption} units
                </p>

                <p>
                    <strong>Net Export:</strong>
                    ${summary.monthlyNetExport} units
                </p>

            </div>

        `;

    } catch (error) {

        console.error(error);

        document.getElementById("summaryResult").innerHTML =
            `<p>Error: ${error.message}</p>`;
    }
}


// ======================================================
// DAILY NET EXPORT
// ======================================================

async function getNetExport() {

    const householdId =
        document.getElementById("netExportHouseholdId").value;

    const date =
        document.getElementById("netExportDate").value;

    if (!householdId || !date) {

        alert("Please enter household ID and date.");

        return;
    }

    try {

        const response = await fetch(
            `${BASE_URL}/solar-share/net-export/${householdId}?date=${date}`
        );

        if (!response.ok) {
            throw new Error(await response.text());
        }

        const result =
            await response.json();

        const container =
            document.getElementById("netExportResult");

        container.innerHTML = `

            <div class="result-card">

                <h3>Daily Net Export</h3>

                <p>
                    <strong>Household ID:</strong>
                    ${result.householdId}
                </p>

                <p>
                    <strong>Date:</strong>
                    ${result.date}
                </p>

                <p>
                    <strong>Net Export:</strong>
                    ${result.netExport} units
                </p>

            </div>

        `;

    } catch (error) {

        console.error(error);

        document.getElementById("netExportResult").innerHTML =
            `<p>Error: ${error.message}</p>`;
    }
}


// ======================================================
// DASHBOARD
// ======================================================

async function loadDashboard() {

    try {

        // ----------------------------------------------
        // GET GENERATION LOGS
        // ----------------------------------------------

        const generationResponse =
            await fetch(`${BASE_URL}/generation-logs`);

        if (!generationResponse.ok) {
            throw new Error("Failed to load generation data");
        }

        const generations =
            await generationResponse.json();


        // ----------------------------------------------
        // GET CONSUMPTION LOGS
        // ----------------------------------------------

        const consumptionResponse =
            await fetch(`${BASE_URL}/consumption-logs`);

        if (!consumptionResponse.ok) {
            throw new Error("Failed to load consumption data");
        }

        const consumptions =
            await consumptionResponse.json();


        // ----------------------------------------------
        // GET HOUSEHOLDS
        // ----------------------------------------------

        const householdResponse =
            await fetch(`${BASE_URL}/households`);

        if (!householdResponse.ok) {
            throw new Error("Failed to load household data");
        }

        const households =
            await householdResponse.json();


        // ----------------------------------------------
        // CALCULATE TOTAL GENERATION
        // ----------------------------------------------

        const totalGeneration =
            generations.reduce(
                (total, generation) =>
                    total +
                    Number(generation.unitsGenerated || 0),
                0
            );


        // ----------------------------------------------
        // CALCULATE TOTAL CONSUMPTION
        // ----------------------------------------------

        const totalConsumption =
            consumptions.reduce(
                (total, consumption) =>
                    total +
                    Number(consumption.unitsConsumed || 0),
                0
            );


        // ----------------------------------------------
        // CALCULATE TOTAL NET EXPORT
        // ----------------------------------------------

        const totalExport =
            Math.max(
                totalGeneration - totalConsumption,
                0
            );


        // ----------------------------------------------
        // UPDATE DASHBOARD
        // ----------------------------------------------

        document.getElementById("totalGeneration").textContent =
            `${totalGeneration} kWh`;

        document.getElementById("totalConsumption").textContent =
            `${totalConsumption} kWh`;

        document.getElementById("totalExport").textContent =
            `${totalExport} kWh`;

        document.getElementById("totalHouseholds").textContent =
            households.length;


    } catch (error) {

        console.error(
            "Dashboard error:",
            error
        );

    }
}


// ======================================================
// PAGE LOAD
// ======================================================

document.addEventListener(
    "DOMContentLoaded",
    () => {

        // Dashboard
        loadDashboard();

        // Installation
        loadInstallations();

        // Household
        loadHouseholds();

        // Generation
        loadGenerations();

        // Consumption
        loadConsumptions();

    }
);