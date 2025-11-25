// Base URL do servidor
const baseUrl = 'http://localhost:7078';

// Helper para fetch com token
function fetchWithToken(url, options = {}, token = null) {
    const headers = { 'Content-Type': 'application/json' };
    if (token) headers['Authorization'] = token;
    return fetch(url, { ...options, headers });
}

// Helper para parse response
async function parseResponse(response) {
    return await response.json();
}

// Helper para log
function log(msg) { console.log(msg); }

// Testes
async function runTests() {
    log('Iniciando testes API Estacionamento...');
    
    // 1. Login admin
    log('1. Login admin...');
    let response = await fetchWithToken(`${baseUrl}/login`, {
        method: 'POST',
        body: JSON.stringify({ username: 'admin', password: 'admin123' })
    });
    let data = await parseResponse(response);
    log(`Login admin: ${response.status} - ${JSON.stringify(data)}`);
    const adminToken = response.ok ? data.token : null;
    
    // 2. Logout admin
    if (adminToken) {
        log('2. Logout admin...');
        response = await fetchWithToken(`${baseUrl}/logout`, { method: 'PUT' }, adminToken);
        data = await parseResponse(response);
        log(`Logout admin: ${response.status} - ${JSON.stringify(data)}`);
    }
    
    // 3. Login novamente
    log('3. Login admin novamente...');
    response = await fetchWithToken(`${baseUrl}/login`, {
        method: 'POST',
        body: JSON.stringify({ username: 'admin', password: 'admin123' })
    });
    data = await parseResponse(response);
    log(`Login admin: ${response.status} - ${JSON.stringify(data)}`);
    const newToken = response.ok ? data.token : null;
    
    if (!newToken) {
        log('Falha no login, abortando.');
        return;
    }
    
    // 4. Criar user teste
    log('4. Criar user teste...');
    response = await fetchWithToken(`${baseUrl}/api/admin/users`, {
        method: 'POST',
        body: JSON.stringify({ username: 'teste', password: '123456', role: 'USER' })
    }, newToken);
    data = await parseResponse(response);
    log(`Criar user: ${response.status} - ${JSON.stringify(data)}`);
    
    // 5. Login como user teste
    log('5. Login user teste...');
    response = await fetchWithToken(`${baseUrl}/login`, {
        method: 'POST',
        body: JSON.stringify({ username: 'teste', password: '123456' })
    });
    data = await parseResponse(response);
    log(`Login teste: ${response.status} - ${JSON.stringify(data)}`);
    const userToken = response.ok ? data.token : null;
    
    if (!userToken) {
        log('Falha no login user, abortando.');
        return;
    }
    
    // 6. Listar estadias ativas (deve estar vazio)
    log('6. Listar estadias ativas...');
    response = await fetchWithToken(`${baseUrl}/api/parking`, {}, userToken);
    data = await parseResponse(response);
    log(`Listar estadias: ${response.status} - ${JSON.stringify(data)}`);
    
    // 7. Criar estadia
    log('7. Criar estadia...');
    response = await fetchWithToken(`${baseUrl}/api/parking`, {
        method: 'POST',
        body: JSON.stringify({ plate: 'ABC-1234', model: 'Fusca', color: 'Azul' })
    }, userToken);
    data = await parseResponse(response);
    log(`Criar estadia: ${response.status} - ${JSON.stringify(data)}`);
    
    // 8. Listar estadias ativas (deve ter 1)
    log('8. Listar estadias ativas novamente...');
    response = await fetchWithToken(`${baseUrl}/api/parking`, {}, userToken);
    data = await parseResponse(response);
    log(`Listar estadias: ${response.status} - ${JSON.stringify(data)}`);
    const stayId = data.list && data.list.length > 0 ? data.list[0].id : null;
    
    if (stayId) {
        // 9. Finalizar estadia
        log('9. Finalizar estadia...');
        response = await fetchWithToken(`${baseUrl}/api/parking/${stayId}`, { method: 'PUT' }, userToken);
        data = await parseResponse(response);
        log(`Finalizar estadia: ${response.status} - ${JSON.stringify(data)}`);
        
        // 10. Listar histórico
        log('10. Listar histórico...');
        response = await fetchWithToken(`${baseUrl}/api/parking/history`, {}, userToken);
        data = await parseResponse(response);
        log(`Histórico: ${response.status} - ${JSON.stringify(data)}`);
    }
    
    // 11. Logout user
    log('11. Logout user...');
    response = await fetchWithToken(`${baseUrl}/logout`, { method: 'PUT' }, userToken);
    data = await parseResponse(response);
    log(`Logout user: ${response.status} - ${JSON.stringify(data)}`);
    
    // 12. Admin listar todas as estadias
    log('12. Admin listar todas as estadias...');
    response = await fetchWithToken(`${baseUrl}/api/admin/parking`, {}, newToken);
    data = await parseResponse(response);
    log(`Admin estadias: ${response.status} - ${JSON.stringify(data)}`);
    
    // 13. Logout admin final
    log('13. Logout admin final...');
    response = await fetchWithToken(`${baseUrl}/logout`, { method: 'PUT' }, newToken);
    data = await parseResponse(response);
    log(`Logout admin: ${response.status} - ${JSON.stringify(data)}`);
    
    log('Testes finalizados.');
}

// Executar
runTests().catch(err => log('Erro nos testes: ' + err));