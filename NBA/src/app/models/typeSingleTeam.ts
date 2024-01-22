export type teamStatistic = {
    team: {
        id: number;
        nbaFranchise: boolean;
        teamName: string;
        city: string;
        allStar: boolean;
        code: string;
        nickname: string;
        logo: string;
        conferenceName: string;
        divisionName: string;
    };
    season: number;
    games: number;
    fastBreakPoints: number;
    pointsInPaint: number;
    biggestLead: number;
    secondChancePoints: number;
    pointsOffTurnover: number;
    points: number;
    fgm: number;
    fga: number;
    fgp: number;
    ftm: number;
    fta: number;
    ftp: number;
    tpm: number;
    tpa: number;
    tpp: number;
    offReb: number;
    defReb: number;
    totReb: number;
    assists: number;
    steals: number;
    turnovers: number;
    blocks: number;
    plusMinus: number;
    pfouls: number;
    conferenceRank: number;
};

export type teamCalendar = {
    gameid: number;
    gameStartDate: string;
    matchDate: string;
    matchTime: string;
    teamHomeName: string;
    teamAwayName: string;
    scoreTeamHome: number;
    scoreTeamAway: number;
    conferenceNameHome: string;
    conferenceNameAway: string;
    divisionNameHome: string;
    divisionNameAway: string;
    teamIdHome: number;
    teamIdAway: number;
    allStarHome: false;
    allStarAway: false;
    nicknameHome: string;
    nicknameAway: string;
    logoHome: string;
    logoAway: string;
    cityHome: string;
    cityAway: string;
    codeHome: string;
    codeAway: string;
};

export type matchCalendar = {
    previousMatch: teamCalendar[];
    nextMatch: teamCalendar[];
    totalMatch: teamCalendar[];
};

type playerStats = {
    id: number;
    nome: string;
    points: number;
    pos: string;
    min: string;
    fgm: number;
    fga: number;
    fgp: number;
    ftm: number;
    fta: number;
    ftp: number;
    tpm: number;
    tpa: number;
    tpp: number;
    offReb: number;
    defReb: number;
    totReb: number;
    assists: number;
    pFouls: number;
    steals: number;
    turnovers: number;
    blocks: number;
    plusMinus: number;
    pfouls: number;
};

export type teamPlayer = {
    team: {
        id: number;
        nbaFranchise: boolean;
        teamName: string;
        city: string;
        allStar: boolean;
        code: string;
        nickname: string;
        logo: string;
        conferenceName: string;
        divisionName: string;
    };
    playerId: number;
    firstName: string;
    lastName: string;
    birthDate: string;
    birthCountry: string;
    nbaStart: number;
    nbaPro: number;
    heightFeet: number;
    heightInches: number;
    heightMeters: number;
    weightPounds: number;
    weightKg: number;
    college: string;
    affiliation: string;
    posizione: string;
    numeroMaglia: number;
    age: number;
    assists: number;
    points: number;
    statistics: playerStats[];
    statisticsArray: [
        giorno1: [number, number][],
        giorno2: [number, number][],
        giorno3: [number, number][],
        giorno4: [number, number][],
        giorno5: [number, number][],
    ]
}[];

export type allPlayer={
    players: teamPlayer[];
}
