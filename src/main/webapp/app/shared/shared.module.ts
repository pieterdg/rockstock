import { NgModule } from '@angular/core';
import { RockstockSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';

// Vanaf hier, zelf toegevoegde components:
import { NickelstrunzSelectorComponent } from './component/nickelstrunz-selector.component';
import { SearchService } from './search/search.service';


import {JhMaterialModule} from 'app/shared/jh-material.module';
@NgModule({
  imports: [JhMaterialModule, RockstockSharedLibsModule],
  declarations: [FindLanguageFromKeyPipe, AlertComponent, AlertErrorComponent, LoginModalComponent, HasAnyAuthorityDirective, NickelstrunzSelectorComponent],
  entryComponents: [LoginModalComponent],
  exports: [JhMaterialModule, 
    RockstockSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    NickelstrunzSelectorComponent
  ],
  providers: [SearchService]
})
export class RockstockSharedModule {}
